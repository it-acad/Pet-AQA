package helper.clean_up;

import lombok.experimental.UtilityClass;

import java.util.HashSet;
import java.util.Set;

@UtilityClass
public class TestDataRemoveCollector {
    private Set<Long> ids = new HashSet<>();

    public Set<Long> getIds() {
        return ids;
    }

    public void addId(Long id) {
        ids.add(id);
    }
}
