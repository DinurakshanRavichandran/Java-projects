public class Job {
    int id;
    String jobName;
    String description;
    Double estimatedTimeInHours;
    Double budget;
    boolean isCompleted;

    public Job(int id, String jobName, String description, Double estimatedTimeInHours, Double budget) {
        this.id = id;
        this.jobName = jobName;
        this.description = description;
        this.estimatedTimeInHours = estimatedTimeInHours;
        this.budget = budget;
        this.isCompleted = false;
    }

    public int getId() {
        return id;
    }

    public String getJobName() {
        return jobName;
    }
}
