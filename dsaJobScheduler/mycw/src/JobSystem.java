
public class JobSystem {
    DynamicJobArray jobArray;
    DependencyGraph dependencyGraph;
    CustomLinkedList readyList;


    public JobSystem(int numJobs) {
        jobArray = new DynamicJobArray();
        dependencyGraph = new DependencyGraph(numJobs);
        readyList = new CustomLinkedList();

    }

    public void addJob(int id, String jobName, String description, double estimatedTimeInHours, double budget) {
        Job job = new Job(id, jobName, description, estimatedTimeInHours, budget);
        jobArray.add(job);
        if (isJobReady(id)) {
            readyList.insert(id);
        }
    }

    private void markJobCompleted(int id) {
        Job job = jobArray.get(id);
        if (job != null && !job.isCompleted) {
            job.isCompleted = true;
        }
    }

    public void addDependency(int job1, int job2) {
        this.dependencyGraph.addDependency(job1, job2);
        System.out.println("Dependency added successfully");
        if (this.readyList.contains(job1)) {
            this.readyList.delete(job1);
        }
    }

    public boolean isJobReady(int jobId) {
        CustomLinkedList dependencies = this.dependencyGraph.getDependencies(jobId);
        if (dependencies != null) {
            for (int i = 0; i < dependencies.size(); ++i) {
                int dependencyId = dependencies.get(i);
                Job dependency = this.jobArray.get(dependencyId);
                if (dependency == null || !dependency.isCompleted) {
                    return false;
                }
            }
        }
        return true;
    }

    public void updateReadyList(int executedJobId) {
        //System.out.println("Updating ready list for job ID: " + executedJobId);
        this.readyList.delete(executedJobId);
       // System.out.println("Ready list after deleting executed job: ");
       // this.readyList.show();
        this.markJobCompleted(executedJobId);

        for (int i = 0; i < this.jobArray.size(); ++i) {
            Job job = this.jobArray.get(i);
            if (job != null && !job.isCompleted) {
                CustomLinkedList dependencies = this.dependencyGraph.getDependencies(job.getId());
                if (dependencies != null && dependencies.contains(executedJobId)) {
                    dependencies.delete(executedJobId);
                }

                boolean allDependenciesCompleted = true;
                if (dependencies != null) {
                    for (Node node = dependencies.head; node != null; node = node.next) {
                        Job dependencyJob = this.jobArray.get(node.data);
                        if (dependencyJob == null || !dependencyJob.isCompleted) {
                            allDependenciesCompleted = false;
                            break;
                        }
                    }
                }

                if (allDependenciesCompleted && !this.readyList.contains(job.getId())) {
                    this.readyList.insert(job.getId());
                   // System.out.println("Added job ID: " + job.getId() + " to ready list");
                }
            }
        }

       // System.out.println("Updated ready list:");
        //this.readyList.show();
    }
    public void showReadyJobs() {
        readyList.show();
    }

    public void showAllJobs() {
        jobArray.show();
    }

    public void executeJob() {
        if (readyList.head == null) {
            System.out.println("Ready list is empty. No job to execute.");
            return;
        }

        int jobId = readyList.get(0);
        System.out.println("Job ID to execute: " + jobId);

        // Check if jobId is within valid range
        if (jobId < 0 || jobId >= jobArray.size()) {
            System.out.println("Invalid job ID: " + jobId);
            return;
        }

        Job job = jobArray.get(jobArray.getIndex(jobId));

        if (job != null) {
            if (!job.isCompleted) {
                System.out.println("Executing job ID: " + jobId);
                readyList.delete(jobId); // Remove the job from the ready list before executing it
                job.isCompleted = true;
                updateReadyList(jobId);
            } else {
                System.out.println("Job ID: " + jobId + " is already completed.");
            }
        } else {
            System.out.println("Job ID: " + jobId + " does not exist.");
        }
    }

    public void seeExecutedJobs() {
        for (int i = 0; i < jobArray.size(); i++) {
            if (jobArray.get(i) != null && jobArray.get(i).isCompleted) {
                System.out.println(jobArray.get(i).id);
            }
        }
    }

    public void seeAllJobs() {
       for (int i = 0; i < jobArray.size(); i++) {
            Job job = jobArray.get(i);
            if (job == null) {
                break;
            }
            System.out.println(job.id);
            System.out.println(job.jobName);
            System.out.println(job.description);
            System.out.println(job.estimatedTimeInHours);
            System.out.println(job.budget);
            System.out.println("\n");
        }
   }





    public void seeAllDependencies(int id) {
       if (id < 0) {
            throw new IllegalArgumentException("Job ID must be a non-negative integer");
        }
        CustomLinkedList dependencies = dependencyGraph.getDependencies(id);
        if (dependencies.isEmpty()) {
            System.out.println("No dependencies therefore ready to execute");
        } else {
            dependencies.show();
        }
    }







}
