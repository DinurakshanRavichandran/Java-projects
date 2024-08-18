public class DependencyGraph {
    public CustomLinkedList[] adjList;

    public DependencyGraph(int numJobs) {
        adjList = new CustomLinkedList[numJobs];
        for (int i = 0; i < numJobs; i++) {
            adjList[i] = new CustomLinkedList();
        }
    }

    public void addDependency(int job1, int job2) {
        if (job1 >= adjList.length || job2 >= adjList.length) {
            throw new IndexOutOfBoundsException("Job ID exceeds capacity: job1 = " + job1 + ", job2 = " + job2);
        }
        adjList[job1].insert(job2);
    }

    public CustomLinkedList getDependencies(int job) {
        if (job >= adjList.length) {
            throw new IndexOutOfBoundsException("Job ID exceeds capacity: " + job);
        }
        return adjList[job];
    }
}
