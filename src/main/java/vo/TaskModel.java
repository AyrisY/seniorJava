package vo;

/**
 * @author yangjie
 * @date 2018/1/1
 * @time 下午1:30
 */
public class TaskModel {

    private String time;
    private String name;

    public TaskModel(String time, String name) {
        this.time = time;
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "TaskModel{" +
                "time='" + time + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
