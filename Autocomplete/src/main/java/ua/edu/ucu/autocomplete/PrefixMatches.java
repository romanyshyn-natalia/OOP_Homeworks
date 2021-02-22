package ua.edu.ucu.autocomplete;

import ua.edu.ucu.immutable.Queue;
import ua.edu.ucu.tries.RWayTrie;
import ua.edu.ucu.tries.Trie;
import ua.edu.ucu.tries.Tuple;

import java.util.Iterator;

/**
 * @author andrii
 */
public class PrefixMatches {

    private final Trie trie;

    public PrefixMatches(Trie trie) {
        this.trie = trie;
    }

    public PrefixMatches() {
        this.trie = new RWayTrie();
    }


    public int load(String... strings) {
        // Формує in-memory словник слів. Метод може приймати слово,
        // рядок, масив слів/рядків.
        // Якщо приходить рядок, то він має бути розділений на окремі слова
        // (слова відокремлюються пробілами).
        // До словника мають додаватися лише слова довші за 2 символи.
        // Передбачається, що у рядках які надходять знаки пунктуації відсутні.
        String[] tokens;
        for (String line : strings) {
            tokens = line.split("\\s+");
            for (String token : tokens) {
                if (token.length() > 2) {
                    trie.add(new Tuple(token, tokens.length));
                }
            }
        }
        return size();

    }

    public boolean contains(String word) {
        // Чи є слово у словнику
        return trie.contains(word);
    }

    public void delete(String word) {
        trie.delete(word);
    }

    public Iterable<String> wordsWithPrefix(String pref) {
        // Якщо pref довший або дорівнює 2ом символам, то повертається усі слова
        // які починаються з даного префіксу.
        if (pref.length() < 2) {
            throw new IllegalArgumentException("Prefix should be "
                                               + "longer or equal 2");
        }
        return trie.wordsWithPrefix(pref);
    }

    public Iterable<String> wordsWithPrefix(String pref, int k) {
        // Якщо pref довший або дорівнює 2ом символам,
        // то повертається набір слів k
        // різних довжин (починаючи з довжини префіксу
        // або 3 якщо префікс містить дві літери)
        // і які починаються з даного префіксу pref.
        // Приклад: задані наступні слова та їх довжини:
        // abc 3
        // abcd 4
        // abce 4
        // abcde 5
        // abcdef 6
        // Вказано префікс pref='abc',
        // - при k=1 повертається 'abc'
        // - при k=2 повертається 'abc', 'abcd', 'abce'
        // - при k=3 повертається 'abc', 'abcd', 'abce', 'abcde'
        // - при k=4 повертається 'abc', 'abcd',
        // 'abce', 'abcde', 'abcdef'
        if (pref.length() < 2) {
            throw new IllegalArgumentException("Prefix should be "
                                               + "longer or equal 2");
        }
        Iterator<String> target = trie.wordsWithPrefix(pref).iterator();
        String token;
        Queue filtered = new Queue();
        while (target.hasNext()) {
            token = target.next();
            if (token.length() - pref.length() < k) {
                filtered.enqueue(token);
            }
        }
        return filtered::iterator;
    }

    public int size() {
        // Кількість слів у словнику
        return trie.size();
    }
}
