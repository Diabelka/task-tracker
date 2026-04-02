public class SubTask extends Task {

    private int epicIndex;

    public int getEpicIndex() {
        return epicIndex;
    }

    public void setEpicIndex(int epikIndex) {
        this.epicIndex = epikIndex;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                " epicIndex=" + epicIndex +
                " id=" + id +
                ", title='" + title + '\'' +
                ", status=" + status +
                '}';
    }
}
