package com.wender.activitatbotonera;

import com.google.firebase.firestore.Blob;
import com.google.firebase.firestore.DocumentReference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Escultura {

    private String idEscultura;
    private Map<String, String> nom;
    private Map<String, String> material;
    private Double altura;
    private Double amplada;
    private Double pes;
    private Integer any;
    private Map<String, Blob> audio;
    private List<Blob> imatges;
    private Double latitud;
    private Double longitud;
    private DocumentReference artista;

    public Escultura() {
        nom = new HashMap<String, String>();
        material = new HashMap<String, String>();
        audio = new HashMap<String, Blob>();
        imatges = new ArrayList<Blob>();
    }

    public Escultura(String idEscultura, Map<String, String> nom, Map<String, String> material,
                     Double altura, Double amplada, Double pes, Integer any,
                     Map<String, Blob> audio, List<Blob> imatges, Double latitud, Double longitud) {
        this();
        setIdEscultura(idEscultura);
        setAltura(altura);
        setAmplada(amplada);
        setPes(pes);
        setAny(any);
        setLatitud(latitud);
        setLongitud(longitud);
    }

    public String getIdEscultura() {
        return idEscultura;
    }

    public void setIdEscultura(String idEscultura) {
            this.idEscultura = idEscultura;
    }

    public Map<String, String> getNom() {
        return nom;
    }

    public void setNom(Map<String, String> nom) {
        this.nom = nom;
    }

    public Map<String, String> getMaterial() {
        return material;
    }

    public void setMaterial(Map<String, String> material) {
        this.material = material;
    }

    public Double getAltura() {
        return altura;
    }

    public void setAltura(Double altura) {
            this.altura = altura;
    }

    public Double getAmplada() {
        return amplada;
    }

    public void setAmplada(Double amplada) {
            this.amplada = amplada;
    }

    public Double getPes() {
        return pes;
    }

    public void setPes(Double pes) {
            this.pes = pes;
    }

    public Integer getAny() {
        return any;
    }

    public void setAny(Integer any) {
            this.any = any;
    }

    public Map<String, Blob> getAudio() {
        return audio;
    }

    public void setAudio(Map<String, Blob> audio) {
        this.audio = audio;
    }

    public List<Blob> getImatges() {
        return imatges;
    }

    public void setImatges(List<Blob> imatges) {
        this.imatges = imatges;
    }

    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
            this.latitud = latitud;
    }

    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
            this.longitud = longitud;
    }

    public DocumentReference getArtista() { return artista; }

    public void setArtista(DocumentReference artista) { this.artista = artista; }
}