public class TestTries {
    public static void main(String[] args) {
        Trie trie = new Trie();

        trie.insert("app");
        trie.insert("apex");
        trie.insert("bat");
        trie.insert("ball");
        trie.insert("cat");

        trie.PrintTrie();
        trie.PrintWord(trie.root,"");
        
    }
}
