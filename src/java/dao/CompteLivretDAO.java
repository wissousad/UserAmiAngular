/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;

/**
 *
 * @author Ouissal
 */
public interface CompteLivretDAO {

    public void save(CompteLivretEntity c);

    public void update(CompteLivretEntity c);

    public void delete(CompteLivretEntity c);

    public CompteLivretEntity find(Long id);

    public List<CompteLivretEntity> findAll();

    public List<CompteLivretEntity> findAllByProfessionnel(Long id);
}
