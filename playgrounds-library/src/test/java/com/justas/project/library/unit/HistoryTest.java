package com.justas.project.library.unit;

import com.justas.project.library.model.History;
import com.justas.project.library.services.HistoryService;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class HistoryTest {
    private final HistoryService historyService = new HistoryService();

    @Test
    public void historyShouldSaveInformationCorrectly() {
        int childId = 1;
        int playgroundId = 2;
        assertThat(historyService.getHistoryList()).isEmpty();
        historyService.childJoinedIntoPlayground(childId, playgroundId);
        assertThat(historyService.getHistoryList()).size().isEqualTo(1);
        History history = historyService.getHistoryList().get(0);
        assertThat(history.getChildId()).isEqualTo(childId);
        assertThat(history.getPlaygroundId()).isEqualTo(playgroundId);
        assertThat(history.getStartTime().getHour()).isEqualTo(LocalDateTime.now().getHour());
        assertThat(history.getEndTime()).isNull();
        assertThat(history.getStayDuration()).isNull();
    }

    @Test
    public void historyShouldUpdateInformationCorrectly() {
        int childId = 1;
        int playgroundId = 2;
        historyService.childJoinedIntoPlayground(childId, playgroundId);
        historyService.childLeftPlayground(childId, playgroundId);
        History history = historyService.getHistoryList().get(0);
        testCommonInfoAboutHistory(history, childId, playgroundId);
        assertThat(history.getEndTime().getHour()).isEqualTo(LocalDateTime.now().getHour());
        assertThat(history.getStayDuration()).isPositive();
    }

    private void testCommonInfoAboutHistory(History history,
                                            int childId,
                                            int playgroundId) {
        assertThat(history.getChildId()).isEqualTo(childId);
        assertThat(history.getPlaygroundId()).isEqualTo(playgroundId);

    }
}
