/*
 * Copyright 2020 Prathab Murugan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.prathab.shopping.services.map;

import com.prathab.shopping.domain.BaseEntity;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

public abstract class AbstractMapService<T extends BaseEntity, ID extends Long> {
  protected Map<Long, T> mMap = new HashMap<>();

  public Set<T> findAll() {
    return new HashSet<>(mMap.values());
  }

  public T findById(ID id) {
    return mMap.get(id);
  }

  public T save(T object) {
    if (object != null) {
      if (object.getId() == null) {
        object.setId(getNextID());
      }
      mMap.put(object.getId(), object);
    } else {
      throw new RuntimeException("Object cannot be null");
    }
    return object;
  }

  public void delete(T object) {
    mMap.entrySet().removeIf(entry -> entry.getValue().equals(object));
  }

  public void deleteById(ID id) {
    mMap.remove(id);
  }

  private Long getNextID() {
    Long nextId = null;

    try {
      nextId = Collections.max(mMap.keySet()) + 1;
    } catch (NoSuchElementException e) {
      nextId = 1L;
    }
    return nextId;
  }
}
