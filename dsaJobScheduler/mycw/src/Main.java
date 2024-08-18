import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        JobSystem system = new JobSystem(100); // Assuming a maximum of 100 jobs

        while (true) {
            System.out.println("Choose an action:");
            System.out.println("1. Add a job");
            System.out.println("2. Add a dependency");
            System.out.println("3. Execute the next job");
            System.out.println("4. Show all jobs");
            System.out.println("5. Show ready jobs");
            System.out.println("6. see executed jobs");
            System.out.println("7. Exit");
            System.out.print("Select an option from 1 - 6: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter job ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter job name: ");
                    String jobName = scanner.nextLine();
                    System.out.print("Enter job description: ");
                    String description = scanner.nextLine();
                    System.out.print("Enter estimated time in hours: ");
                    double estimatedTimeInHours = scanner.nextDouble();
                    System.out.print("Enter job budget: ");
                    double budget = scanner.nextDouble();

                    system.addJob(id, jobName, description, estimatedTimeInHours, budget);
                    System.out.println("Job added successfully.");
                    break;

                case 2:
                    System.out.print("Enter the ID of the dependant: ");
                    int job1 = scanner.nextInt();
                    System.out.print("Enter the ID of the job it depends on: ");
                    int job2 = scanner.nextInt();
                    system.addDependency(job1, job2);
                    break;

                case 3:
                    system.executeJob();
                    break;

                case 4:
                    system.showAllJobs();
                    break;

                case 5:
                    system.showReadyJobs();
                    break;

                case 6:
                    system.seeExecutedJobs();
                    break;

                case 7:
                    System.out.println("Quitting the system.");
                    scanner.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
