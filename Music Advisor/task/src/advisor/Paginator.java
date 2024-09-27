package advisor;

import java.util.List;

public class Paginator {
    private final List<String> list;
    private final int pageSize;
    private int currentPage;

    public Paginator(List<String> list, int pageSize) {
        this.list = list;
        this.pageSize = pageSize;
        this.currentPage = 0;
    }

    public List<String> getCurrentPage() {
        int start = currentPage * pageSize;
        int end = Math.min(start + pageSize, list.size());
        if (start >= list.size()) {
            return List.of();
        }
        return list.subList(start, end);
    }

    public boolean hasNext() {
        return (currentPage + 1) * pageSize < list.size();
    }

    public boolean hasPrev() {
        return currentPage > 0;
    }

    public void nextPage() {
        if (hasNext()) {
            currentPage++;
        }
    }

    public void prevPage() {
        if (hasPrev()) {
            currentPage--;
        }
    }

    public int getCurrentPageNumber() {
        return currentPage + 1;
    }

    public int getTotalPages() {
        return (int) Math.ceil((double) list.size() / pageSize);
    }
}
