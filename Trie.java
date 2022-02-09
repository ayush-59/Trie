package Trie;

import java.util.ArrayList;

public class Trie {
	TrieNode root;
	
	private class TrieNode{
		TrieNode[] nodes;
		boolean endOfWord;
		
		public TrieNode() {
			nodes = new TrieNode[26];
			endOfWord = false;
		}
	}
	
	public Trie() {
		root = new TrieNode();
	}
	
	public void insert(String s) {
		s = s.toLowerCase();
	
		TrieNode curr = root;
		for(int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			int index = c - 'a';
			if(curr.nodes[index] == null)
				curr.nodes[index] = new TrieNode();
			curr = curr.nodes[index];
			if(i == s.length() - 1)
				curr.endOfWord = true;
		}
	}
	
	public boolean isEmpty(TrieNode root) {
		for(int i = 0; i < 26; i++) {
			if(root.nodes[i] != null)
				return false;
		}
		return true;
	}
	
	public boolean search(String s) {
		s = s.toLowerCase();
		TrieNode curr = root;
		int index=0;
		for(int i = 0; i < s.length(); i++) {
			index = s.charAt(i) - 'a';
			curr = curr.nodes[index];
			if(curr == null)
				return false;
			
		}
		if(curr.endOfWord)
			return true;
		return false;
	}
	
	public ArrayList<String> autocomplete(String s) {
		ArrayList<String> result = new ArrayList<>();
		s = s.toLowerCase();
		TrieNode curr = root;
		int index=0;
		for(int i = 0; i < s.length(); i++) {
			index = s.charAt(i) - 'a';
			curr = curr.nodes[index];
			if(curr == null)
				return result;
			
		}
		
		getSuggestions(curr, s, result);
		return result;
	}
	
	public void getSuggestions(TrieNode root, String s, ArrayList<String> result) {
		if(root.endOfWord) {
			result.add(s);
		}
	
		for(int i = 0; i < 26; i++) {
			if(result.size() == 3)
				return;
			if(root.nodes[i] != null) {
				getSuggestions(root.nodes[i], s+(char)(i+'a'), result);
			}
		}
		 
	}
	
	public void delete(String s) {
		s = s.toLowerCase();
		root = delete(root, s, 0);
	}
	
	private TrieNode delete(TrieNode root, String s, int i) {
		if(root == null)
			return null;
		
		if(i == s.length()) {
			if(root.endOfWord) {
				root.endOfWord = false;
				if(isEmpty(root))
					root = null;
			}				
			return root;
		}
		int index = s.charAt(i) - 'a';
		
		root.nodes[index] = delete(root.nodes[index], s, i+1);
		
		if(root != this.root && isEmpty(root))
			root = null;
		return root;
		
	}
	
	public static void main(String[] args) {
		Trie t = new Trie();
		t.insert("simmu");
		t.insert("sim");
		t.insert("samir");
		t.insert("sanya");
		System.out.println(t.search("sim"));
		System.out.println(t.search("simmu"));
		
		
		System.out.println(t.search("sim"));
		System.out.println(t.search("simmu"));
		t.insert("ayush");
		System.out.println(t.search("ayush"));
		System.out.println(t.autocomplete("si"));
	}
	
}
