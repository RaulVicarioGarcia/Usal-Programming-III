import com.elloza.youtubemetadata.VideoInfoExtractor;
import com.github.kiulian.downloader.model.videos.VideoInfo;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class App {

    private static Map<String, VideoInfo> videoCollection = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n=== YouTube Video Manager ===");
            System.out.println("1. Create (Add a new video)");
            System.out.println("2. Read (Show all videos)");
            System.out.println("3. Update (Update video info)");
            System.out.println("4. Delete (Remove a video)");
            System.out.println("5. Search (Find videos by keyword)");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    createVideo();
                    break;
                case 2:
                    readVideos();
                    break;
                case 3:
                    updateVideo();
                    break;
                case 4:
                    deleteVideo();
                    break;
                case 5:
                    searchVideos();
                    break;
                case 6:
                    System.out.println("Exiting... Goodbye!");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void createVideo() {
        System.out.print("Enter YouTube video URL: ");
        String url = scanner.nextLine();

        if (videoCollection.containsKey(url)) {
            System.out.println("Video already exists in the collection.");
            return;
        }

        try {
            VideoInfo videoInfo = VideoInfoExtractor.getVideoInfoFromUrl(url);
            videoCollection.put(url, videoInfo);
            System.out.println("Video added successfully!");
        } catch (Exception e) {
            System.out.println("Failed to add video: " + e.getMessage());
        }
    }

    private static void readVideos() {
        if (videoCollection.isEmpty()) {
            System.out.println("No videos in the collection.");
            return;
        }

        System.out.printf("%-50s %-30s %-20s %-10s\n", "URL", "Title", "Author", "Views");
        System.out.println("-".repeat(120));

        for (Map.Entry<String, VideoInfo> entry : videoCollection.entrySet()) {
            String url = entry.getKey();
            VideoInfo videoInfo = entry.getValue();

            System.out.printf("%-50s %-30s %-20s %-10d\n",
                    url,
                    videoInfo.details().title(),
                    videoInfo.details().author(),
                    videoInfo.details().viewCount());
        }
    }

    private static void updateVideo() {
        System.out.print("Enter YouTube video URL to update: ");
        String url = scanner.nextLine();

        if (!videoCollection.containsKey(url)) {
            System.out.println("Video not found in the collection.");
            return;
        }

        try {
            VideoInfo videoInfo = VideoInfoExtractor.getVideoInfoFromUrl(url);
            videoCollection.put(url, videoInfo);
            System.out.println("Video updated successfully!");
        } catch (Exception e) {
            System.out.println("Failed to update video: " + e.getMessage());
        }
    }

    private static void deleteVideo() {
        System.out.print("Enter YouTube video URL to delete: ");
        String url = scanner.nextLine();

        if (videoCollection.remove(url) != null) {
            System.out.println("Video removed successfully!");
        } else {
            System.out.println("Video not found in the collection.");
        }
    }

    private static void searchVideos() {
        System.out.print("Enter keyword to search: ");
        String keyword = scanner.nextLine().toLowerCase();

        boolean found = false;
        System.out.printf("%-50s %-30s %-20s %-10s\n", "URL", "Title", "Author", "Views");
        System.out.println("-".repeat(120));

        for (Map.Entry<String, VideoInfo> entry : videoCollection.entrySet()) {
            String url = entry.getKey();
            VideoInfo videoInfo = entry.getValue();
            String title = videoInfo.details().title().toLowerCase();

            if (title.contains(keyword)) {
                found = true;
                System.out.printf("%-50s %-30s %-20s %-10d\n",
                        url,
                        videoInfo.details().title(),
                        videoInfo.details().author(),
                        videoInfo.details().viewCount());
            }
        }

        if (!found) {
            System.out.println("No videos found with the keyword: " + keyword);
        }
    }
}
