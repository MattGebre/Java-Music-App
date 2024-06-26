import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.io.IOException;

/*
 * This class manages, stores, and plays audio content such as songs, podcasts and audiobooks. 
 */

public class Library
{
	private ArrayList<Song> 			songs; 
	private ArrayList<Podcast> 		podcasts;
	private ArrayList<AudioBook> 	audiobooks;
	private ArrayList<Playlist> 	playlists; 
		
	// Public methods in this class set errorMsg string 
	// Error Messages can be retrieved from main in class MyAudioUI by calling  getErrorMessage()
	// In assignment 2 we will replace this with Java Exceptions

	String errorMsg = "";
	
	public String getErrorMessage()
	{
		return errorMsg;
	}

	public Library()
	{
		songs 			= new ArrayList<Song>(); 
		audiobooks 	= new ArrayList<AudioBook>(); ;
		playlists   = new ArrayList<Playlist>(); 
		podcasts   = new ArrayList<Podcast>();
	}
	/*
	 * Download audio content from the store. Since we have decided (design decision) to keep 3 separate lists in our library
	 * to store our songs, podcasts and audiobooks (we could have used one list) then we need to look at the type of
	 * audio content to determine which list it belongs to above
	 * 
	 * Make sure you do not add song/podcast/audiobook to a list if it is already there. Hint: use the equals() method
	 * If it is already in a list, set the errorMsg string and return false. Otherwise add it to the list and return true
	 */
	public void download(AudioContent content)  
	{
		if (content.getType().equals(Song.TYPENAME))
		{
			if (songs.contains(content))
			{
				throw new IllegalArgumentException("Song" + content.getTitle() + " already downloaded");
			}
			songs.add((Song)content);
		}
		else if (content.getType().equals(AudioBook.TYPENAME))
		{
			if (audiobooks.contains(content))
			{
				throw new IllegalArgumentException("Audiobook" + content.getTitle() + " already downloaded");
			}
			audiobooks.add((AudioBook)content);
		}
		else if (content.getType().equals(Podcast.TYPENAME)) 
		{
			if (podcasts.contains(content)) 
			{
				throw new IllegalArgumentException("Podcast" + content.getTitle() + " already downloaded");
			}
			podcasts.add((Podcast) content);
		}
	
	// Print Information (printInfo()) about all songs in the array list
	public void listAllSongs()
	{
		for (int i = 0; i < songs.size(); i++)
		{
			int index = i + 1;
			System.out.print(index + ". ");
			songs.get(i).printInfo();
			System.out.println();	
		}
	}
	
	// Print Information (printInfo()) about all audiobooks in the array list
	public void listAllAudioBooks()
	{
		for (int i = 0; i < audiobooks.size(); i++)
		{
			int index = i + 1;
			System.out.print(index + ". ");
			audiobooks.get(i).printInfo();
			System.out.println();	
		}
	}

	// Print Information (printInfo()) about all podcasts in the array list
	public void listAllPodcasts()
	{
		for (int i = 0; i < podcasts.size(); i++)
		{
			int index = i+1;
			System.out.print(index + ". ");
			podcasts.get(i).printInfo();
		}
	}
	
  // Print the name of all playlists in the playlists array list
	// First print the index number as in listAllSongs() above
	public void listAllPlaylists()
	{
		for (int i = 0; i < playlists.size(); i++)
		{
			int index = i+1;
			System.out.println(index + ". " +  playlists.get(i).getTitle());
		}
	}
	
  // Print the name of all artists. 
	// Go through the songs array list and add to an arraylist only if it is
	// not already there. Once the artist arraylist is complete, print the artists names
	public void listAllArtists()
	{
		ArrayList<String> artists = new ArrayList<String>();
		
		for (Song song : songs)
		{
			if (!artists.contains(song.getArtist()))
				artists.add(song.getArtist());
		}
		for (int i = 0; i < artists.size(); i++)
		{
			int index = i+1;
			System.out.println(index + ". " + artists.get(i));
		}
	}

	// Delete a song from the library (i.e. the songs list) - 
	// also go through all playlists and remove it from any playlist as well
	public void deleteSong(int index)
	{
		if (index < 1 || index > songs.size())
		{
			throw new IllegalArgumentException("Song not found");
		}
		Song song = songs.remove(index-1);
		
		// Search all playlists
		for (int i = 0; i < playlists.size(); i++)
		{
			Playlist pl = playlists.get(i);
			if (pl.getContent().contains(song))
				pl.getContent().remove(song);
		}
		
	}
	
  //Sort songs in library by year
	public void sortSongsByYear()
	{
		Collections.sort(songs, new SongYearComparator());
	}

	private class SongYearComparator implements Comparator<Song>
	{
		public int compare(Song a, Song b)
		{
			if (a.getYear() > b.getYear()) return 1;
			if (a.getYear() < b.getYear()) return -1;	
			return 0;
		}
	}

	// Sort songs by length
	public void sortSongsByLength()
	{
		Collections.sort(songs, new SongLengthComparator());
	}

	private class SongLengthComparator implements Comparator<Song>
	{
		public int compare(Song a, Song b)
		{
			if (a.getLength() > b.getLength()) return 1;
			if (a.getLength() < b.getLength()) return -1;	
			return 0;
		}
	}

	// Sort songs by title (Comparable)
	public void sortSongsByName()
	{
		Collections.sort(songs);
	}

	
	
	/*
	 * Play Content
	 */
	
	// Play song from songs list
	public void playSong(int index)
	{
		if (index < 1 || index > songs.size())
		{
			throw new IllegalArgumentException("Song not found");
		}
		songs.get(index-1).play();
	}
	
	// Play podcast from list
	public void playPodcast(int index, int season, int episode) 
	{
		if (index < 1 || index > podcasts.size()) 
		{
			throw new IllegalArgumentException("Podcast not found");
		}
		Podcast podcast = podcasts.get(index - 1);
		if (season < 1 || season > podcast.getSeasons().size()) 
		{
			throw new IllegalArgumentException("Season not found");
		}
	
		if (index < 1 || index > podcast.getSeasons().get(season - 1).episodeTitles.size()) 
		{
			throw new IllegalArgumentException("Episode not found");
		}
		podcast.setSeason(season - 1);
		podcast.setEpisode(episode - 1);
		podcast.play();
	}

	public void printPodcastEpisodes(int index, int season) 
	{
		if (index < 1 || index > podcasts.size()) 
		{
			throw new IllegalArgumentException("Podcast not found");
		}
		Podcast podcast = podcasts.get(index - 1);
		podcast.printSeasonEpisodes(season);
	}
	
	// Play audio book from list
	public void playAudioBook(int index, int chapter)
	{
		if (index < 1 || index > audiobooks.size())
		{
			throw new IllegalArgumentException("Audiobook not found");
		}
		AudioBook book = audiobooks.get(index-1);
		if (chapter < 1 || chapter > book.getNumberOfChapters())
		{
			throw new IllegalArgumentException("Chapter not found");
		}
		book.selectChapter(chapter);
		book.play();
	}
	
	public void printAudioBookTOC(int index)
	{
		if (index < 1 || index > audiobooks.size())
		{
			throw new IllegalArgumentException("Audiobook not found");
		}
		AudioBook book = audiobooks.get(index-1);
		book.printTOC();
	}
	
  /*
   * Playlist Related Methods
   */
	
	// Make a new playlist 
	// Make sure a playlist with the same title doesn't already exist
	public void makePlaylist(String title)
	{
		Playlist pl = new Playlist(title);
		if (playlists.contains(pl))
		{
				throw new IllegalArgumentException("Playlist " + title + " Already Exists");
		}
		playlists.add(pl);
	}
	
	// Print list of content (songs, audiobooks etc) in playlist named title from list of playlists
	public void printPlaylist(String title)
	{
		int index = playlists.indexOf(new Playlist(title));
		
		if (index == -1)
		{
			throw new IndexOutOfBoundsException("Playlist not found");
		}
		playlists.get(index).printContents();
	}
	
	// Play all content in a playlist
	public void playPlaylist(String playlistTitle)
	{
		int index = playlists.indexOf(new Playlist(playlistTitle));
		
		if (index == -1)
		{
			throw new IndexOutOfBoundsException("Playlist not found");
		}
		playlists.get(index).playAll();
	}
	
	// Play a specific song/audiobook in a playlist
	public void playPlaylist(String playlistTitle, int index)
	{
    int plIndex = playlists.indexOf(new Playlist(playlistTitle));
		
		if (plIndex == -1)
		{
			throws new IndexOutOfBoundsException("Playlist not found");
		}
		Playlist pl = playlists.get(plIndex);
		
		if (index < 1 || index > pl.getContent().size())
		{
			throw new IndexOutOfBoundsException("Invalid Playlist AudioContent #");
		}
		System.out.println(pl.getTitle());
		
		// Play chapter 1 if this is an audio book. Could also set it to play all
		if (pl.getContent(index).getType().equals(AudioBook.TYPENAME))
		{
			AudioBook book = (AudioBook) pl.getContent(index);
			book.selectChapter(1);
		}
		
		pl.play(index);
	}
	
	// Add a song/audiobook/podcast from library to a playlist
	public void addContentToPlaylist(String type, int index, String playlistTitle)
	{
		AudioContent ac = null;
		
		if (type.equalsIgnoreCase("SONG"))
		{
			if (index < 1 || index > songs.size())
			{
			throw new IllegalArgumentException("Song not found");
			}
			ac = songs.get(index-1);
		}
		else if (type.equalsIgnoreCase("AUDIOBOOK"))
		{
			if (index < 1 || index > audiobooks.size())
			{
			throw new IllegalArgumentException("Audiobook not found");
			}
			ac = audiobooks.get(index-1);
		}
		else if (type.equalsIgnoreCase("PODCAST"))
		{
			if (index < 1 || index > podcasts.size())
			{
			throw new IllegalArgumentException("Podcast not found");
			}
			ac = podcasts.get(index-1);
		}
	}

  // Delete a song/audiobook/podcast from a playlist
	// Make sure the index is valid
	public void delContentFromPlaylist(int index, String playlistTitle)
	{
		int plIndex = playlists.indexOf(new Playlist(playlistTitle));
		
		if (plIndex == -1)
		{
			throw new IndexOutOfBoundsException("Playlist not found");
		}
		Playlist pl = playlists.get(plIndex);
		
		// Delete Content
		if (!pl.contains(index))
		{
			throw new IndexOutOfBoundsException("Content not in playlist");
		}
		pl.deleteContent(index);
	}
}
/*
public class AudioContentNotFoundExecption extends RunTimeException 
	{ 
		public AudioContentNotFoundExecption(String message) 
		{
			super(message);
		}
	}

public class PlaylistNotFoundException extends RuntimeException 
{
    public PlaylistNotFoundException(String message) 
{
        super(message);
    }
}

public class PlaylistAlreadyExistsException extends RuntimeException 
{
    public PlaylistAlreadyExistsException(String message) 
	{
        super(message);
    }
}

public class InvalidPlaylistContentIndexException extends RuntimeException 
{
    public InvalidPlaylistContentIndexException(String message) 
	{
        super(message);
    }
}

public class ContentNotInPlaylistException extends RuntimeException 
{
    public ContentNotInPlaylistException(String message) 
	{
        super(message);
    }
}
*/