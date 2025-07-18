package arraylist;

public class Task{
    private String description;
    private boolean isDone;

    public Task(String description){
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone(){
        this.isDone = true;
    }

    public boolean isDone(){
        return isDone;
    }

    public String toString(){
        return (isDone ? "[X] " : "[ ] ") + description;
    }

    public String getDescription(){
        return description;
    }
    public void setDescription(String new_description){
        this.description = new_description;
    }
}
