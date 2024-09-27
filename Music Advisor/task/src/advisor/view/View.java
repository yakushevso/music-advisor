package advisor.view;

import java.util.List;

public interface View {
    void showPage(List<String> list);
    void showMessage(Messages messages);
    void showPageInfo(int currentPage, int totalPages);
}

