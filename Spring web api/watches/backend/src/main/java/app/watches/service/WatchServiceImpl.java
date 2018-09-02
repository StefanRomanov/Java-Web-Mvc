package app.watches.service;

import app.watches.domain.entities.Watch;
import app.watches.domain.models.service.WatchServiceModel;
import app.watches.repo.WatchRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class WatchServiceImpl implements WatchService{

    private final WatchRepository watchRepository;
    private final ModelMapper modelMapper;


    @Autowired
    public WatchServiceImpl(WatchRepository watchRepository, ModelMapper modelMapper) {
        this.watchRepository = watchRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public boolean createWatch(WatchServiceModel model) {

        Watch watch = this.modelMapper.map(model,Watch.class);

        try{
            this.watchRepository.saveAndFlush(watch);
        } catch (Exception e){
            return false;
        }

        return true;
    }

    @Override
    public Set<WatchServiceModel> allWatches() {
        return this.watchRepository.findAll().stream()
                .map(e -> this.modelMapper.map(e, WatchServiceModel.class))
                .collect(Collectors.toUnmodifiableSet());
    }

    @Override
    public Set<WatchServiceModel> topFourByViews() {
        return this.watchRepository.findTop4ByOrderByViewsDesc().stream()
                .map(e -> this.modelMapper.map(e, WatchServiceModel.class))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    @Override
    public WatchServiceModel getWatchById(String id) {

        Watch watch = this.watchRepository.getOne(id);

        if (watch == null){
            return null;
        }

        this.addView(watch);
        return this.modelMapper.map(watch, WatchServiceModel.class);
    }

    private void addView(Watch watch){
        watch.setViews(watch.getViews() + 1);
        this.watchRepository.saveAndFlush(watch);
    }
}
