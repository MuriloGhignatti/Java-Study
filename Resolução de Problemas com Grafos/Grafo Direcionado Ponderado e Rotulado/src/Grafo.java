import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class Grafo<T> {

    private HashMap<T, Vertice<T>> vertices;

    private enum Resultado{
        Vertice1NaoPresente,
        Vertice2NaoPresente,
        VerticesPresentes,
    }

    public Grafo(HashMap<T, Vertice<T>> toCopy){
        this.vertices = toCopy;
    }

    public Grafo(Grafo<T> toCopy){
        this.vertices = toCopy.vertices;
    }

    public Grafo(){
        this.vertices = new HashMap<>();
    }

    private Resultado verticesExistem(T vertice1, T vertice2){
        if(!existeVertice(vertice1)){
            return Resultado.Vertice1NaoPresente;
        }
        else if(!existeVertice(vertice2)){
            return Resultado.Vertice2NaoPresente;
        }
        return Resultado.VerticesPresentes;
    }

    public void addVertice(T info){
        vertices.put(info, new Vertice<T>(info));
    }

    public void addVertice(Vertice<T> vertice){
        vertices.put(vertice.getInfo(), vertice);
    }

    public void addVertice(T info, Vertice<T> vertice){
        vertices.put(info, vertice);
    }

    public void removeVertice(T info){
        vertices.remove(info);
    }

    public void removeVertice(Vertice<T> vertice){
        vertices.remove(vertice.getInfo());
    }

    public void removeVertice(T info, Vertice<T> vertice){
        vertices.remove(info, vertice);
    }

    public boolean existeVertice(T info){
        return vertices.containsKey(info);
    }

    public boolean existeVertice(Vertice<T> vertice){
        return vertices.containsKey(vertice.getInfo());
    }

    public boolean existeVertice(T info, Vertice<T> vertice){
        return vertices.containsKey(info) && vertices.containsValue(vertice);
    }

    public void imprimeAdjacencias(T info){
        vertices.get(info).imprimeAdjacencias();
    }

    public void imprimeAdjacencias(Vertice<T> vertice){
        vertices.get(vertice.getInfo()).imprimeAdjacencias();
    }

    public int adjacentes(T info, Collection<Aresta<T>> listaAdjacentes){
        listaAdjacentes = vertices.get(info).getArestas();
        return listaAdjacentes.size();
    }

    public int adjacentes(Vertice<T> vertice, Collection<Aresta<T>> listaAdjacentes){
        listaAdjacentes = vertices.get(vertice.getInfo()).getArestas();
        return listaAdjacentes.size();
    }

    public void setInfo(T verticeInfo, T newInfo){
        Vertice vertice = vertices.remove(verticeInfo);
        vertice.setInfo(newInfo);
        vertices.put(newInfo, vertice);
    }

    public void setInfo(Vertice<T> vertice, T newInfo){
        Vertice holder = vertices.remove(vertice.getInfo());
        holder.setInfo(newInfo);
        vertices.put(newInfo, holder);
    }

    public int[][] gerarMatrizAdjacencias(){
        ArrayList<Vertice<T>> values = new ArrayList<>(vertices.values());
        int[][] matriz = new int[vertices.size()][vertices.size()];
        for (int i = 0; i < vertices.size(); i++) {
            for (int j = 0; j < vertices.size(); j++) {
                matriz[i][j] = values.get(i).existeAdjacencia(values.get(j));
            }
        }
        return matriz;
    }

    public void criaAdjacencia(T vertice1, T vertice2, int p, String rotulo){
        switch (verticesExistem(vertice1, vertice2)){
            case Vertice1NaoPresente    -> System.err.println("Vertice: " + vertice1 + " não encontrado no grafo, impossivel criar adjacencia");
            case Vertice2NaoPresente    -> System.err.println("Vertice: " + vertice2 + " não encontrado no grafo, impossivel criar adjacencia");
            case VerticesPresentes      -> vertices.get(vertice1).addAresta(new Aresta<T>(vertices.get(vertice2), p, rotulo));
        }
    }

    public void criaAdjacencia(T vertice1, T vertice2, int p){
        switch (verticesExistem(vertice1, vertice2)) {
            case Vertice1NaoPresente    -> System.err.println("Vertice: " + vertice1 + " não encontrado no grafo, impossivel criar adjacencia");
            case Vertice2NaoPresente    -> System.err.println("Vertice: " + vertice2 + " não encontrado no grafo, impossivel criar adjacencia");
            case VerticesPresentes      -> vertices.get(vertice1).addAresta(new Aresta(vertices.get(vertice2), p));
        }
    }

    public void criaAdjacencia(T vertice1, T vertice2){
        switch (verticesExistem(vertice1, vertice2)) {
            case Vertice1NaoPresente    -> System.err.println("Vertice: " + vertice1 + " não encontrado no grafo, impossivel criar adjacencia");
            case Vertice2NaoPresente    -> System.err.println("Vertice: " + vertice2 + " não encontrado no grafo, impossivel criar adjacencia");
            case VerticesPresentes      -> vertices.get(vertice1).addAresta(new Aresta(vertices.get(vertice2)));
        }
    }

    public void criaAdjacencia(Vertice<T> vertice1, Vertice<T> vertice2, int p, String rotulo){
        switch (verticesExistem(vertice1.getInfo(), vertice2.getInfo())) {
            case Vertice1NaoPresente    -> System.err.println("Vertice: " + vertice1 + " não encontrado no grafo, impossivel criar adjacencia");
            case Vertice2NaoPresente    -> System.err.println("Vertice: " + vertice2 + " não encontrado no grafo, impossivel criar adjacencia");
            case VerticesPresentes      -> vertices.get(vertice1.getInfo()).addAresta(new Aresta(vertice2, p, rotulo));
        }
    }

    public void criaAdjacencia(Vertice<T> vertice1, Vertice<T> vertice2, int p){
        switch (verticesExistem(vertice1.getInfo(), vertice2.getInfo())) {
            case Vertice1NaoPresente    -> System.err.println("Vertice: " + vertice1 + " não encontrado no grafo, impossivel criar adjacencia");
            case Vertice2NaoPresente    -> System.err.println("Vertice: " + vertice2 + " não encontrado no grafo, impossivel criar adjacencia");
            case VerticesPresentes      -> vertices.get(vertice1.getInfo()).addAresta(new Aresta(vertice2, p));
        }
    }

    public void criaAdjacencia(Vertice<T> vertice1, Vertice<T> vertice2){
        switch (verticesExistem(vertice1.getInfo(), vertice2.getInfo())) {
            case Vertice1NaoPresente    -> System.err.println("Vertice: " + vertice1 + " não encontrado no grafo, impossivel criar adjacencia");
            case Vertice2NaoPresente    -> System.err.println("Vertice: " + vertice2 + " não encontrado no grafo, impossivel criar adjacencia");
            case VerticesPresentes      -> vertices.get(vertice1.getInfo()).addAresta(new Aresta(vertice2));
        }
    }

    public void criaAdjacencia(T vertice1, Vertice<T> vertice2, int p, String rotulo){
        switch (verticesExistem(vertice1, vertice2.getInfo())) {
            case Vertice1NaoPresente    -> System.err.println("Vertice: " + vertice1 + " não encontrado no grafo, impossivel criar adjacencia");
            case Vertice2NaoPresente    -> System.err.println("Vertice: " + vertice2 + " não encontrado no grafo, impossivel criar adjacencia");
            case VerticesPresentes      -> vertices.get(vertice1).addAresta(new Aresta(vertice2, p, rotulo));
        }
    }

    public void criaAdjacencia(T vertice1, Vertice<T> vertice2, int p){
        switch (verticesExistem(vertice1, vertice2.getInfo())) {
            case Vertice1NaoPresente    -> System.err.println("Vertice: " + vertice1 + " não encontrado no grafo, impossivel criar adjacencia");
            case Vertice2NaoPresente    -> System.err.println("Vertice: " + vertice2 + " não encontrado no grafo, impossivel criar adjacencia");
            case VerticesPresentes      -> vertices.get(vertice1).addAresta(new Aresta(vertice2, p));
        }
    }

    public void criaAdjacencia(T vertice1, Vertice<T> vertice2){
        switch (verticesExistem(vertice1, vertice2.getInfo())) {
            case Vertice1NaoPresente    -> System.err.println("Vertice: " + vertice1 + " não encontrado no grafo, impossivel criar adjacencia");
            case Vertice2NaoPresente    -> System.err.println("Vertice: " + vertice2 + " não encontrado no grafo, impossivel criar adjacencia");
            case VerticesPresentes      -> vertices.get(vertice1).addAresta(new Aresta(vertice2));
        }
    }

    public void criaAdjacencia(Vertice<T> vertice1, T vertice2, int p, String rotulo){
        switch (verticesExistem(vertice1.getInfo(), vertice2)) {
            case Vertice1NaoPresente    -> System.err.println("Vertice: " + vertice1 + " não encontrado no grafo, impossivel criar adjacencia");
            case Vertice2NaoPresente    -> System.err.println("Vertice: " + vertice2 + " não encontrado no grafo, impossivel criar adjacencia");
            case VerticesPresentes      -> vertices.get(vertice1.getInfo()).addAresta(new Aresta(vertices.get(vertice2), p, rotulo));
        }
    }

    public void criaAdjacencia(Vertice<T> vertice1, T vertice2, int p){
        switch (verticesExistem(vertice1.getInfo(), vertice2)) {
            case Vertice1NaoPresente    -> System.err.println("Vertice: " + vertice1 + " não encontrado no grafo, impossivel criar adjacencia");
            case Vertice2NaoPresente    -> System.err.println("Vertice: " + vertice2 + " não encontrado no grafo, impossivel criar adjacencia");
            case VerticesPresentes      -> vertices.get(vertice1.getInfo()).addAresta(new Aresta(vertices.get(vertice2), p));
        }
    }

    public void criaAdjacencia(Vertice<T> vertice1, T vertice2){
        switch (verticesExistem(vertice1.getInfo(), vertice2)) {
            case Vertice1NaoPresente    -> System.err.println("Vertice: " + vertice1 + " não encontrado no grafo, impossivel criar adjacencia");
            case Vertice2NaoPresente    -> System.err.println("Vertice: " + vertice2 + " não encontrado no grafo, impossivel criar adjacencia");
            case VerticesPresentes      -> vertices.get(vertice1.getInfo()).addAresta(new Aresta(vertices.get(vertice2)));
        }
    }

    public void removeAdjacencia(Vertice<T> vertice1, Vertice<T> vertice2){
        switch (verticesExistem(vertice1.getInfo(), vertice2.getInfo())){
            case Vertice1NaoPresente    -> System.err.println("Vertice: " + vertice1 + " não encontrado no grafo, impossivel remover adjacencia");
            case Vertice2NaoPresente    -> System.err.println("Vertice: " + vertice2 + " não encontrado no grafo, impossivel remover adjacencia");
            case VerticesPresentes      -> vertices.get(vertice1.getInfo()).removeAresta(vertice2);
        }
    }

    public void removeAdjacencia(Vertice<T> vertice1, T vertice2){
        switch (verticesExistem(vertice1.getInfo(), vertice2)){
            case Vertice1NaoPresente    -> System.err.println("Vertice: " + vertice1 + " não encontrado no grafo, impossivel remover adjacencia");
            case Vertice2NaoPresente    -> System.err.println("Vertice: " + vertice2 + " não encontrado no grafo, impossivel remover adjacencia");
            case VerticesPresentes      -> vertices.get(vertice1.getInfo()).removeAresta(vertice2);
        }
    }

    public void removeAdjacencia(T vertice1, T vertice2){
        switch (verticesExistem(vertice1, vertice2)){
            case Vertice1NaoPresente    -> System.err.println("Vertice: " + vertice1 + " não encontrado no grafo, impossivel remover adjacencia");
            case Vertice2NaoPresente    -> System.err.println("Vertice: " + vertice2 + " não encontrado no grafo, impossivel remover adjacencia");
            case VerticesPresentes      -> vertices.get(vertice1).removeAresta(vertice2);
        }
    }

    public void removeAdjacencia(T vertice1, Vertice<T> vertice2){
        switch (verticesExistem(vertice1, vertice2.getInfo())){
            case Vertice1NaoPresente    -> System.err.println("Vertice: " + vertice1 + " não encontrado no grafo, impossivel remover adjacencia");
            case Vertice2NaoPresente    -> System.err.println("Vertice: " + vertice2 + " não encontrado no grafo, impossivel remover adjacencia");
            case VerticesPresentes      -> vertices.get(vertice1).removeAresta(vertice2);
        }
    }
}
