public class Codec {
    private Map<String, String> code2Url = new HashMap<>();
    private int count = 0;
    private static final String prefixUrl = "http://tinyurl.com/";

    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        String code = Integer.toHexString(++count);
        code2Url.put(code, longUrl);
        return prefixUrl + code;
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        String code = shortUrl.replace(prefixUrl, "");
        return code2Url.get(code);
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.decode(codec.encode(url));