using System.Collections.Generic;
using System.Linq;

class TrieNode {
    public bool IsEnd { get; set; }
    public TrieNode[] Children { get; set; }
    public TrieNode() {
        Children = new TrieNode[26];
    }
}

public class WordDictionary {
    private TrieNode root;

    public WordDictionary() {
        root = new TrieNode();
    }

    public void AddWord(string word) {
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
        var queue = new Queue<TrieNode>();
        queue.Enqueue(root);
        for (var i = 0; i < word.Length; ++i)
        {
            var count = queue.Count;
            while (count-- > 0)
            {
                var node = queue.Dequeue();
                if (word[i] == '.')
                {
                    foreach (var nextNode in node.Children)
                    {
                        if (nextNode != null)
                        {
                            queue.Enqueue(nextNode);
                        }
                    }
                }
                else
                {
                    var nextNode = node.Children[word[i] - 'a'];
                    if (nextNode != null)
                    {
                        queue.Enqueue(nextNode);
                    }
                }
            }
        }
        return queue.Any(n => n.IsEnd);
    }
}