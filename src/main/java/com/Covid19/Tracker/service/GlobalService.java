package com.Covid19.Tracker.service;

import com.Covid19.Tracker.dto.GlobalDto;
import com.Covid19.Tracker.exceptions.DateException;
import com.Covid19.Tracker.model.Global;
import com.Covid19.Tracker.repository.GlobalRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
public class GlobalService {
    private final ModelMapper modelMapper;
    private final GlobalRepository globalRepository;

    public GlobalService(ModelMapper modelMapper, GlobalRepository globalRepository) {
        this.modelMapper = modelMapper;
        this.globalRepository = globalRepository;
    }
   public ResponseEntity<?> findByDate(String... dates){
       DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
       dateTimeFormatter = dateTimeFormatter.withLocale(Locale.US);
       if(dates.length == 0) {
           return new ResponseEntity<>(new DateException("No date was given!"), HttpStatus.BAD_REQUEST);
       }
       else if (dates.length == 1){
           LocalDate localDate = LocalDate.parse(dates[0],dateTimeFormatter);
           Global global = globalRepository.findByDate(localDate);
           GlobalDto globalDto = modelMapper.map(global,GlobalDto.class);
           return new ResponseEntity<GlobalDto>(globalDto, HttpStatus.OK);
       }
       else {
           LocalDate[] localDates = new LocalDate[2];
           for (int i = 0; i < dates.length; i++){
               localDates[i] = LocalDate.now(); //init. values
               localDates[i] = LocalDate.parse(dates[i],dateTimeFormatter);
           }
           List<Global> globals = globalRepository.getAllBetweenDates(localDates[0],localDates[1]);
           List<GlobalDto> globalDtos = globals
                   .stream()
                   .map(global -> modelMapper.map(global,GlobalDto.class))
                   .collect(Collectors.toList());
           return new ResponseEntity<List<GlobalDto>>(globalDtos,HttpStatus.OK);
       }
    }
}
