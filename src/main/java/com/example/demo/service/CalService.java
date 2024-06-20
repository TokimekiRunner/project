package com.example.demo.service;

import com.example.demo.entity.Everything;

import java.util.List;

public interface CalService {
  List<Everything> calwork(List<Everything> everythings);
  float caltotal(List<Everything> everythings);

}
