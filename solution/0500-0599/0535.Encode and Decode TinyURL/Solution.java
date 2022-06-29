public class Codec {
    private Map<String, String> m = new HashMap<>();
    private int idx = 0;
    private String domain = "https://tinyurl.com/";

    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        String v = String.valueOf(++idx);
        m.put(v, longUrl);
        return domain + v;
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        int i = shortUrl.lastIndexOf('/') + 1;
        return m.get(shortUrl.substring(i));
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.decode(codec.encode(url));