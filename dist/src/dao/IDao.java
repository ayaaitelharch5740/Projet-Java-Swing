/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;

/**
 *
 * @author ORIGINAL SHOP
 */
public interface IDao<T> {
    T findById(int id ) throws Exception ;
    List<T> findAll()   throws Exception ;
    boolean insert (T o) throws Exception ;
    boolean update (T o) throws Exception ;
    boolean delete (T o) throws Exception ;
    
    
    
}
