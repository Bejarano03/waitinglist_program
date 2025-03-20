import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class WaitingListManager {
    private Queue<String> waitingList;
    private Set<String> waitingListSet;

    public WaitingListManager() {
        waitingList = new LinkedList<>();
        waitingListSet = new HashSet<>();
    }

    public void addPerson(String name) {
        if (waitingList.contains(name)) {
            System.out.println(name + " is already in the waiting list.");
        } else {
            waitingList.add(name);
            waitingListSet.add(name);
            System.out.println(name + " has been added to the waiting list.");
        }
    }

    public String servePerson() {
        if (waitingList.isEmpty()) {
            return "The waiting list is empty.";
        } else {
            String servedPerson = waitingList.poll();
            waitingListSet.remove(servedPerson);
            return servedPerson;
        }
    }

    public boolean isPersonInList(String name) {
        return waitingListSet.contains(name);
    }

    public int waitingListSize() {
        return waitingList.size();
    }

    public void displayWaitingList() {
        if (waitingList.isEmpty()) {
            System.out.println("The waiting list is empty.");
        } else {
            System.out.println("Waiting List:");
            int position = 1;
            for (String person : waitingList) {
                System.out.println(position + ". " + person);
                position++;
            }
        }
    }

}
