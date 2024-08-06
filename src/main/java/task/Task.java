package task;

import java.util.List;

public class Task {
    private String massage;
    private List<Subtask> subtasks;


    public void getMassage() {
        if (subtask==null){
            System.out.print(massage);
        }else {
            for (Subtask subtask:subtask) {
                System.out.print(massage+": ");
                subtask.getMassage();
                Performance.performance();
            }
        }
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }


    public List<Subtask> getSubtask() {
        return subtask;
    }

    public void setSubtask(List<Subtask> subtask) {
        this.subtask = subtask;
    }
}
