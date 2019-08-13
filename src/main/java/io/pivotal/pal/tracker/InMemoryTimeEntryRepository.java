package io.pivotal.pal.tracker;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class InMemoryTimeEntryRepository implements TimeEntryRepository{

    private Map<Long,TimeEntry> timeEntryMap = new ConcurrentHashMap<>();

    private long id = 1;

    public TimeEntry create(TimeEntry timeEntry) {
        timeEntry.setId(id++);
        timeEntryMap.put(timeEntry.getId(),timeEntry);
        return timeEntry;
    }

    public TimeEntry find(long id) {
        return timeEntryMap.get(id);

    }

    public List<TimeEntry>  list() {
        return timeEntryMap.values().stream().collect(Collectors.toList());
    }

    public TimeEntry update(long id, TimeEntry timeEntry) {

        if(!timeEntryMap.containsKey(id))
            return null;

        timeEntry.setId(id);
        timeEntryMap.put(timeEntry.getId(),timeEntry);
        return timeEntry;
    }

    public void delete(long id) {
        timeEntryMap.remove(id);

    }
}
