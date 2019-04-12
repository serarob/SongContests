/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import model.DAO.SongDAO;
import model.entity.Song;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Padelis
 */
@Repository
public class SongDAOImpl implements SongDAO {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Song getSongRand(List<Song> list) {
        //List<Song> list=new ArrayList();
        Random myrandom = new Random();

        int index = myrandom.nextInt(list.size());

        Song song = list.get(index);

        return song;
    }

    @Override
    public Song getSongById(Long sid) {
        String sql = "select * from song where sid = ?";
        Song song = (Song) jdbcTemplate.queryForObject(sql, new Object[]{sid}, new RowMapper<Song>() {
            @Override
            public Song mapRow(ResultSet rs, int i) throws SQLException {
                Song song = new Song();
                song.setSid(rs.getLong("sid"));
                song.setTitle(rs.getString("title"));
                song.setUrlsample(rs.getString("urlsample"));
                song.setQuestion(rs.getString("question"));
                song.setAnswerone(rs.getString("answerone"));
                song.setAnswertwo(rs.getString("answertwo"));
                song.setAnswerthree(rs.getString("answerthree"));
                song.setAnswerfour(rs.getString("answerfour"));
                song.setCorrect(rs.getString("correct"));
                song.setUrlfullsong(rs.getString("urlfullsong"));
                return song;
            }
        });
        return song;
    }

    @Override
    public void insertSongQuestion(Song song) {
        String sql = "insert into song values(default, ?, ?, ?, ?, ?, ?,?,?,?) ";
        System.out.println("DAO called!");
        jdbcTemplate.update(sql, new Object[]{
            song.getTitle(), song.getUrlsample(), song.getQuestion(), song.getAnswerone(),
            song.getAnswertwo(), song.getAnswerthree(), song.getAnswerfour(), song.getCorrect(),
            song.getUrlfullsong()});
    }

    public void updateSongQuestion(Song song) {
        String sql = "UPDATE song SET title=?, urlsample=?, question=?, answerone=?, answertwo=?, answerthree=?,answerfour=?,correct=?,urlfullsong=?, WHERE sid=?";
        jdbcTemplate.update(sql, new Object[]{song.getTitle(), song.getUrlsample(), song.getQuestion(), song.getAnswerone(),
            song.getAnswertwo(), song.getAnswerthree(), song.getAnswerfour(), song.getCorrect(),
            song.getUrlfullsong(), song.getSid()
        });
        System.out.println("bingo");
    }

    @Override
    public void deleteSongQuestion(Long sid) {
        String sql = "DELETE FROM song WHERE sid=?";
        jdbcTemplate.update(sql, new Object[]{sid});
    }

    public List<Song> getAllSongQuestions() {
        String sql = "SELECT * FROM song";

        List<Song> songList = jdbcTemplate.query(sql, new ResultSetExtractor<List<Song>>() {
            @Override
            public List<Song> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<Song> list = new ArrayList<Song>();
                while (rs.next()) {
                    Song song = new Song();
                    song.setSid(rs.getLong("sid"));
                    song.setTitle(rs.getString("title"));
                    song.setUrlsample(rs.getString("urlsample"));
                    song.setQuestion(rs.getString("question"));
                    song.setAnswerone(rs.getString("answerone"));
                    song.setAnswertwo(rs.getString("answertwo"));
                    song.setAnswerthree(rs.getString("answerthree"));
                    song.setAnswerfour(rs.getString("answerfour"));
                    song.setCorrect(rs.getString("correct"));
                    song.setUrlfullsong(rs.getString("urlfullsong"));
                    list.add(song);
                }
                return list;
            }

        });
        return songList;
    }

    /*@Override
    public List<Song> getSongs(List<Song> list, int numberOfSongs) {
        if (numberOfSongs > list.size()) {
            throw new IllegalArgumentException("not enough elements");
        }
        Random random = new Random();
        return IntStream
                .generate(() -> random.nextInt(list.size()))
                .distinct()
                .limit(numberOfSongs)
                .mapToObj(list::get)
                .collect(Collectors.toList());
    }*/

}
