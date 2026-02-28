import java.util.Objects;

public class Task {
    protected String title;
    protected String description;
    protected int id;
    protected String status;


    public void setDescription(String description) {
        this.description = description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public enum Status {
        NEW,
        IN_PROGRESS,
        DONE
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (this.getClass() != obj.getClass()) return false;
        Task otherTask = (Task) obj;
        return Objects.equals(this.title, otherTask.title)
                && Objects.equals(this.description, otherTask.description);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        if (title != null) {
            hash = hash + title.hashCode();
        }
        hash = hash * 31;

        if (description != null) {
            hash = hash + description.hashCode();
        }

        hash = hash * 31;
        return hash;
    }


}
