package com.neu.edu.oms.service;

import com.neu.edu.oms.entity.PaperScanFull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface DataAnalysisService {
    Map<String, Object > getSegmentData(List<PaperScanFull> papers);

    List<Float> getratebardata(List<PaperScanFull> papers);

    Map<String, Object> getgoalradardata(List<PaperScanFull> papers);

    Map<String, Object> getpointradardata(List<PaperScanFull> papers);
}
