package app.watches.web.controllers;

import app.watches.domain.models.binding.CreateWatchBindingModel;
import app.watches.domain.models.service.WatchServiceModel;
import app.watches.domain.models.view.AllWatchesViewModel;
import app.watches.domain.models.view.TopWatchesViewModel;
import app.watches.domain.models.view.WatchesDetailsViewModel;
import app.watches.service.WatchService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/watches")
public class WatchController {

    private WatchService watchService;
    private ModelMapper modelMapper;

    @Autowired
    public WatchController(WatchService watchService, ModelMapper modelMapper) {
        this.watchService = watchService;
        this.modelMapper = modelMapper;
    }

    @GetMapping(value = "/top", produces = "application/json")
    public Set<TopWatchesViewModel> topFourWatches(){
        Set<WatchServiceModel> models = this.watchService.topFourByViews();
        return models.stream().map(e -> this.modelMapper.map(e,TopWatchesViewModel.class))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    @GetMapping(value = "/all", produces = "application/json")
    public Set<AllWatchesViewModel> allWatches(){
        Set<WatchServiceModel> models = this.watchService.allWatches();
        return models.stream().map(e -> this.modelMapper.map(e,AllWatchesViewModel.class))
                .collect(Collectors.toUnmodifiableSet());
    }

    @GetMapping(value = "/details",produces = "application/json")
    public WatchesDetailsViewModel watchDetails(@RequestParam(name = "id") String id){
        return this.modelMapper.map( this.watchService.getWatchById(id), WatchesDetailsViewModel.class);
    }


    @PostMapping(value = "/add", produces = "application/json")
    public ResponseEntity create(@ModelAttribute CreateWatchBindingModel model) throws URISyntaxException {
        boolean result = this.watchService.createWatch(this.modelMapper.map(model,WatchServiceModel.class));
        return ResponseEntity.created(new URI("/watches/create")).body(result);
    }
}
