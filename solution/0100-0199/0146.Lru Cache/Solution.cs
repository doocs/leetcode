// https://leetcode.com/problems/lru-cache/

using System.Collections.Generic;

public class LRUCache
{
    class Node
    {
        public Node Prev;
        public Node Next;
        public int Key;
        public int Value;
    }

    private Node _head;
    private Node _tail;
    private Dictionary<int, Node> keyMap;
    private readonly int _capacity;

    public LRUCache(int capacity)
    {
        _capacity = capacity;
        keyMap = new Dictionary<int, Node>();
    }

    public int Get(int key)
    {
        Node node;
        if (keyMap.TryGetValue(key, out node))
        {
            MoveToHead(node);
            return node.Value;
        }
        return -1;
    }

    public void Put(int key, int value)
    {
        Node node;
        if (keyMap.TryGetValue(key, out node))
        {
            MoveToHead(node);
            node.Value = value;
        }
        else
        {
            if (keyMap.Count == _capacity)
            {
                if (_capacity > 0)
                {
                    keyMap.Remove(_tail.Key);
                    RemoveTail();
                }
                else
                {
                    return;
                }
            }
            node = new Node() { Key = key, Value = value };
            keyMap.Add(key, node);
            MoveToHead(node);
        }
    }

    private void MoveToHead(Node node)
    {
        if (_head != node)
        {
            if (_head == null)
            {
                _head = node;
                _tail = node;
            }
            else
            {
                if (_tail == node)
                {
                    _tail = node.Prev;
                }
                if (node.Next != null)
                {
                    node.Next.Prev = node.Prev;
                }
                if (node.Prev != null)
                {
                    node.Prev.Next = node.Next;
                }
                node.Next = _head;
                _head.Prev = node;
                _head = node;
            }
        }
    }

    private void RemoveTail()
    {
        if (_tail != null)
        {
            if (_tail.Prev == null)
            {
                _head = null;
                _tail = null;
            }
            else
            {
                _tail = _tail.Prev;
                _tail.Next = null;
            }
        }
    }
}