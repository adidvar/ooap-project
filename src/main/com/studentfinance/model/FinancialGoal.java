public class FinancialGoal {
    private String id;
    private String title;
    private Double targetAmount;
    private Double currentAmount;
    private Date deadline;
    private List<Notification> notifications;

    public void create() {
        // Implementation
    }

    public void updateProgress() {
        // Implementation
    }

    public double calculatePercentage() {
        return (currentAmount / targetAmount) * 100;
    }

    // getters, setters, constructors
}