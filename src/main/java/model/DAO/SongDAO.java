/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.DAO;

import java.util.List;
import model.entity.Song;

/**
 *
 * @author Padelis
 */
public interface SongDAO {
    
    public Song getSongRand(List<Song> list);
    
    //public List<Song> getSongs(List<Song> list, int numberOfSongs);
    
    public Song getSongById(Long sid);
    
    public void insertSongQuestion(Song song);
    
    public void updateSongQuestion(Song song);
    
    public void deleteSongQuestion(Long sid);
    
    public List<Song> getAllSongQuestions();
    
    
    
}
