package xyz.aungpyaephyo.padc.animation.events;

import java.util.List;

import xyz.aungpyaephyo.padc.animation.data.vo.AttractionVO;

/**
 * Created by aung on 8/24/17.
 */

public class APIEvents {

    public static class AttractionsLoadedEvent {
        private List<AttractionVO> attractionList;

        public AttractionsLoadedEvent(List<AttractionVO> attractionList) {
            this.attractionList = attractionList;
        }

        public List<AttractionVO> getAttractionList() {
            return attractionList;
        }
    }

    public static class ErrorLoadingAttractionsEvent {
        private String errorMsg;

        public ErrorLoadingAttractionsEvent(String errorMsg) {
            this.errorMsg = errorMsg;
        }

        public String getErrorMsg() {
            return errorMsg;
        }
    }
}
