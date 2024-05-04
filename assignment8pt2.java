import java.util.ArrayList;
import java.util.List;

class Song {
    private String title;
    private String artist;

    public Song(String title, String artist) {
        this.title = title;
        this.artist = artist;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }
}

interface SongIterator {
    boolean hasNext();
    Song next();
}

interface Playlist {
    SongIterator createIterator();
    void addSong(Song song);
}

class PlaylistImpl implements Playlist {
    private List<Song> songs;

    public PlaylistImpl() {
        this.songs = new ArrayList<>();
    }

    public void addSong(Song song) {
        songs.add(song);
    }

    public SongIterator createIterator() {
        return new PlaylistIterator(songs);
    }
}

class PlaylistIterator implements SongIterator {
    private List<Song> songs;
    private int position;

    public PlaylistIterator(List<Song> songs) {
        this.songs = songs;
        this.position = 0;
    }

    public boolean hasNext() {
        return position < songs.size();
    }

    public Song next() {
        if (hasNext()) {
            Song song = songs.get(position);
            position++;
            return song;
        } else {
            return null; // or throw an exception
        }
    }
}

public class assignment8pt2 {
    public static void main(String[] args) {
        Playlist playlist = new PlaylistImpl();

        playlist.addSong(new Song("Shape of You", "Ed Sheeran"));
        playlist.addSong(new Song("Blinding Lights", "The Weeknd"));
        playlist.addSong(new Song("Someone Like You", "Adele"));

        SongIterator iterator = playlist.createIterator();

        System.out.println("Playlist Songs:");
        while (iterator.hasNext()) {
            Song song = iterator.next();
            System.out.println(song.getTitle() + " by " + song.getArtist());
        }
    }
}
