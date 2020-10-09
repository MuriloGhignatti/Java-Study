public class Token<T> {
    
    private int id;
    private T info;

    public Token(T info, int id){
        this.info = info;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public T getInfo() {
        return info;
    }
}
