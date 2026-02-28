package com.example.projetws;

import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.projetws.beans.Etudiant;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListEtudiantActivity extends AppCompatActivity {

    private ListView listView;
    private RequestQueue requestQueue;
    private List<Etudiant> etudiants = new ArrayList<>();
    private ArrayAdapter<String> adapter;
    private List<String> displayList = new ArrayList<>();

    private static final String LOAD_URL = "http://10.0.2.2/Projet_Lab5/ws/loadEtudiant.php";
    private static final String DELETE_URL = "http://10.0.2.2/Projet_Lab5/ws/deleteEtudiant.php";
    private static final String UPDATE_URL = "http://10.0.2.2/Projet_Lab5/ws/updateEtudiant.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_etudiant);

        listView = findViewById(R.id.listView);
        requestQueue = Volley.newRequestQueue(this);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, displayList);
        listView.setAdapter(adapter);

        chargerEtudiants();

        listView.setOnItemClickListener((parent, view, position, id) -> {
            Etudiant e = etudiants.get(position);
            showOptionsDialog(e);
        });
    }

    private void chargerEtudiants() {
        StringRequest request = new StringRequest(Request.Method.GET, LOAD_URL,
                response -> {
                    Type type = new TypeToken<List<Etudiant>>(){}.getType();
                    etudiants = new Gson().fromJson(response, type);
                    displayList.clear();
                    for (Etudiant e : etudiants) {
                        displayList.add(e.getNom() + " " + e.getPrenom() + " - " + e.getVille());
                    }
                    adapter.notifyDataSetChanged();
                },
                error -> Toast.makeText(this, "Erreur chargement", Toast.LENGTH_SHORT).show()
        );
        requestQueue.add(request);
    }

    private void showOptionsDialog(Etudiant e) {
        String[] options = {"Modifier", "Supprimer"};
        new AlertDialog.Builder(this)
                .setTitle(e.getNom() + " " + e.getPrenom())
                .setItems(options, (dialog, which) -> {
                    if (which == 0) showModifierDialog(e);
                    else showConfirmSupprimer(e);
                }).show();
    }

    private void showConfirmSupprimer(Etudiant e) {
        new AlertDialog.Builder(this)
                .setTitle("Confirmation")
                .setMessage("Supprimer " + e.getNom() + " ?")
                .setPositiveButton("Oui", (dialog, which) -> supprimerEtudiant(e))
                .setNegativeButton("Non", null)
                .show();
    }

    private void supprimerEtudiant(Etudiant e) {
        StringRequest request = new StringRequest(Request.Method.POST, DELETE_URL,
                response -> {
                    Toast.makeText(this, "Supprimé !", Toast.LENGTH_SHORT).show();
                    chargerEtudiants();
                },
                error -> Toast.makeText(this, "Erreur suppression", Toast.LENGTH_SHORT).show()) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("id", String.valueOf(e.getId()));
                return params;
            }
        };
        requestQueue.add(request);
    }

    private void showModifierDialog(Etudiant e) {
        android.widget.EditText input = new android.widget.EditText(this);
        input.setText(e.getNom());
        new AlertDialog.Builder(this)
                .setTitle("Modifier le nom")
                .setView(input)
                .setPositiveButton("Modifier", (dialog, which) -> {
                    String nouveauNom = input.getText().toString();
                    modifierEtudiant(e, nouveauNom);
                })
                .setNegativeButton("Annuler", null)
                .show();
    }

    private void modifierEtudiant(Etudiant e, String nouveauNom) {
        StringRequest request = new StringRequest(Request.Method.POST, UPDATE_URL,
                response -> {
                    Toast.makeText(this, "Modifié !", Toast.LENGTH_SHORT).show();
                    chargerEtudiants();
                },
                error -> Toast.makeText(this, "Erreur modification", Toast.LENGTH_SHORT).show()) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("id", String.valueOf(e.getId()));
                params.put("nom", nouveauNom);
                return params;
            }
        };
        requestQueue.add(request);
    }
}