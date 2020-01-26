using System.Collections.Generic;
using System.Linq;

class TrieNode {
    public bool IsEnd { get; set; }
    public TrieNode[] Children { get; set; }
    public TrieNode() {
        Children = new TrieNode[26];
    }
}

public class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void Insert(string word) {
        var node = root;
        for (var i = 0; i < word.Length; ++i)
        {
            TrieNode nextNode;
            var index = word[i] - 'a';
            nextNode = node.Children[index];
            if (nextNode == null)
            {
                nextNode = new TrieNode();
                node.Children[index] = nextNode;
            }
            node = nextNode;
        }
        node.IsEnd = true;
    }

    public bool Search(string word) {
        var node = root;
        for (var i = 0; i < word.Length; ++i)
        {
            var nextNode = node.Children[word[i] - 'a'];
            if (nextNode == null)
            {
                return false;
            }
            node = nextNode;
        }
        return node.IsEnd;
    }

    public bool StartsWith(string word) {
        var node = root;
        for (var i = 0; i < word.Length; ++i)
        {
            var nextNode = node.Children[word[i] - 'a'];
            if (nextNode == null)
            {
                return false;
            }
            node = nextNode;
        }
        return node.IsEnd || node.Children.Any(c => c != null);
    }
}