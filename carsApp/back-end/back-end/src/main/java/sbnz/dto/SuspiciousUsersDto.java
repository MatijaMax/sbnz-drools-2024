package sbnz.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class SuspiciousUsersDto {
    List<Integer> ids;

    public SuspiciousUsersDto() {
    }
    public SuspiciousUsersDto(List<Integer> ids) {
        this.ids = ids;
    }

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }

    public void setIdsUnique(Set<Integer> ids) {
        this.ids = new ArrayList<>();
        this.ids.addAll(ids);
    }
}
