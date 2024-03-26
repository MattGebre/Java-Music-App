import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.io.IOException;
import javax.swing.text.AbstractDocument.Content;

// Simulation of a Simple Text-based Music App (like Apple Music)

public class MyAudioUI
{
	public static void main(String[] args)
	{
		// Simulation of audio content in an online store
		// The songs, podcasts, audiobooks in the store can be downloaded to your library
		AudioContentStore store = new AudioContentStore();
		
		// Create my music library
		Library library = new Library();

		Scanner scanner = new Scanner(System.in);
		System.out.print(">");

		// Process keyboard actions
		while (scanner.hasNextLine())
		{
			try 
			{
				String action = scanner.nextLine();

				if (action == null || action.equals("")) 
				{
					System.out.print("\n>");
					continue;
				}
				else if (action.equalsIgnoreCase("Q") || action.equalsIgnoreCase("QUIT"))
					return;
				
				else if (action.equalsIgnoreCase("STORE"))	// List all songs
				{
					store.listAll(); 
				}
				else if (action.equalsIgnoreCase("SONGS"))	// List all songs
				{
					library.listAllSongs(); 
				}
				else if (action.equalsIgnoreCase("BOOKS"))	// List all songs
				{
					library.listAllAudioBooks(); 
				}
				else if (action.equalsIgnoreCase("PODCASTS"))	// List all songs
				{
					library.listAllPodcasts(); 
				}
				else if (action.equalsIgnoreCase("ARTISTS"))	// List all songs
				{
					library.listAllArtists(); 
				}
				else if (action.equalsIgnoreCase("PLAYLISTS"))	// List all play lists
				{
					library.listAllPlaylists(); 
				}
				else if (action.equalsIgnoreCase("DOWNLOAD")) 
				{
					try 
					{
						int fromIndex = 0;
						int toIndex = 0;
						System.out.print("Store Content #: ");
					
						for (int i = fromIndex; i <= toIndex; i++)
						{
							library.download(store.getContent(i));
						}
					}

					catch (IllegalArgumentException ex) 
					{
						System.out.println(ex.getMessage());
					}
										
				}
				else if (action.equalsIgnoreCase("DOWNLOADA")) 
				{
					String artist = "";
					
				} 
				else if (action.equalsIgnoreCase("PLAYSONG")) 
				{
					int index = 0;

					System.out.print("Song Number: ");
					if (scanner.hasNextInt())
					{
						index = scanner.nextInt();
					// consume the nl character since nextInt() does not
						scanner.nextLine(); 
					}
					try 
					{
						library.playSong(index);
					} 
					catch (IllegalArgumentException e) 
					{
						System.out.println(e.getMessage());
					}
				}
				else if (action.equalsIgnoreCase("BOOKTOC")) 
				{
					int index = 0;

					System.out.print("Audio Book Number: ");
					if (scanner.hasNextInt())
					{
						index = scanner.nextInt();
						scanner.nextLine();
					}
					try 
					{
						library.printAudioBookTOC(index);
					} 
					catch (IllegalArgumentException e) 
					{
						System.out.println(e.getMessage());
					}
				}
				else if (action.equalsIgnoreCase("PLAYBOOK")) 
				{
					int index = 0;

					System.out.print("Audio Book Number: ");
					if (scanner.hasNextInt())
					{
						index = scanner.nextInt();
					}
					int chapter = 0;
					System.out.print("Chapter: ");
					if (scanner.hasNextInt())
					{
						chapter = scanner.nextInt();
						scanner.nextLine();
					}
					try 
					{
						library.playAudioBook(index, chapter);
					} 
					catch (IllegalArgumentException e) 
					{
						System.out.println(e.getMessage());
					}
				}
				else if (action.equalsIgnoreCase("PODTOC")) 
				{
					int index = 0;
					int season = 0;
					
					System.out.print("Podcast Number: ");
					if (scanner.hasNextInt())
					{
						index = scanner.nextInt();
					}
					System.out.print("Season: ");
					if (scanner.hasNextInt())
					{
						season = scanner.nextInt();
						scanner.nextLine();
					}	
					try 
					{
						library.printPodcastEpisodes(index, season);
					} 
					catch (IllegalArgumentException e) 
					{
						System.out.println(e.getMessage());
					}
				}
				else if (action.equalsIgnoreCase("PLAYPOD")) 
				{
					int index = 0;

					System.out.print("Podcast Number: ");
					if (scanner.hasNextInt())
					{
						index = scanner.nextInt();
						scanner.nextLine();
					}
					int season = 0;
					System.out.print("Season: ");
					if (scanner.hasNextInt())
					{
						season = scanner.nextInt();
						scanner.nextLine();
					}
					int episode = 0;
					System.out.print("Episode: ");
					if (scanner.hasNextInt())
					{
						episode = scanner.nextInt();
						scanner.nextLine();
					}
					try 
					{
						library.playPodcast(index, season, episode);
					} 
					catch (IllegalArgumentException e) 
					{
						System.out.println(e.getMessage());
					}
				}
				else if (action.equalsIgnoreCase("PLAYALLPL")) 
				{
					String title = "";

					System.out.print("Playlist Title: ");
					if (scanner.hasNextLine())
					{
						title = scanner.nextLine();
					}
					try 
					{
						library.playPlaylist(title);
					} 
					catch (IllegalArgumentException e) 
					{
						System.out.println(e.getMessage());
					}
				}
				else if (action.equalsIgnoreCase("PLAYPL")) 
				{
					String title = "";
					int index = 0;
			
					System.out.print("Playlist Title: ");
					if (scanner.hasNextLine())
					{
						title = scanner.nextLine();
					}
					System.out.print("Content Number: ");
					if (scanner.hasNextInt())
					{
						index = scanner.nextInt();
						scanner.nextLine();
					}
					try 
					{
						library.playPlaylist(title, index);
					} 
					catch (IllegalArgumentException e) 
					{
						System.out.println(e.getMessage());
					}
				}
				// Delete a song from the library and any play lists it belongs to
				else if (action.equalsIgnoreCase("DELSONG")) 
				{
					int songNum = 0;

					System.out.print("Library Song #: ");
					if (scanner.hasNextInt())
					{
						songNum = scanner.nextInt();
						scanner.nextLine();
					}	
					try 
					{
						library.deleteSong(songNum);
					} 
					catch (IllegalArgumentException e) 
					{
						System.out.println(e.getMessage());
					}
				}
				else if (action.equalsIgnoreCase("MAKEPL")) 
				{
					String title = "";

					System.out.print("Playlist Title: ");
					if (scanner.hasNextLine())
					{
						title = scanner.nextLine();
					}
					try 
					{
						library.makePlaylist(title);
					} 
					catch (IllegalArgumentException e) 
					{
						System.out.println(e.getMessage());
					}
				}
				else if (action.equalsIgnoreCase("PRINTPL"))	// print playlist content
				{
					String title = "";

					System.out.print("Playlist Title: ");
					if (scanner.hasNextLine())
					{
						title = scanner.nextLine();
					}
					try 
					{
						library.printPlaylist(title);
					} 
					catch (IllegalArgumentException e) 
					{
						System.out.println(e.getMessage());
					}
				}
				// Add content from library (via index) to a playlist
				else if (action.equalsIgnoreCase("ADDTOPL")) 
				{
					int contentIndex = 0;
					String contentType = "";
					String playlist = "";
			
					System.out.print("Playlist Title: ");
					if (scanner.hasNextLine())
						playlist = scanner.nextLine();
			
					System.out.print("Content Type [SONG, PODCAST, AUDIOBOOK]: ");
					if (scanner.hasNextLine())
						contentType = scanner.nextLine();
					
					System.out.print("Library Content #: ");
					if (scanner.hasNextInt())
					{
						contentIndex = scanner.nextInt();
						scanner.nextLine(); // consume nl
					}
					try 
					{
						library.addContentToPlaylist(contentType, contentIndex, playlist);
					} 
					catch (IllegalArgumentException e) 
					{
						System.out.println(e.getMessage());
					}
				}
				// Delete content from play list
				else if (action.equalsIgnoreCase("DELFROMPL")) 
				{
					int contentIndex = 0;
					String playlist = "";

					System.out.print("Playlist Title: ");
					if (scanner.hasNextLine())
						playlist = scanner.nextLine();
					
					System.out.print("Playlist Content #: ");
					if (scanner.hasNextInt())
					{
						contentIndex = scanner.nextInt();
						scanner.nextLine(); // consume nl
					}
					try 
					{
						library.delContentFromPlaylist(contentIndex, playlist);
					} 
					catch (IllegalArgumentException e) 
					{
						System.out.println(e.getMessage());
					}
				}
				else if (action.equalsIgnoreCase("SORTBYYEAR")) // sort songs by year
				{
					library.sortSongsByYear();
				}
				else if (action.equalsIgnoreCase("SORTBYNAME")) // sort songs by name (alphabetic)
				{
					library.sortSongsByName();
				}
				else if (action.equalsIgnoreCase("SORTBYLENGTH")) // sort songs by length
				{
					library.sortSongsByLength();
				}
					
				System.out.print("\n>");
				
			}
			catch (Exception e) 
			{
				System.out.println(e.getMessage());
			}
		}
	}
}