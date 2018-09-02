package app.watches.service;

import app.watches.domain.models.service.WatchServiceModel;

import java.util.Set;

public interface WatchService {
    boolean createWatch(WatchServiceModel model);
    Set<WatchServiceModel> allWatches();
    Set<WatchServiceModel> topFourByViews();
    WatchServiceModel getWatchById(String id);
}
