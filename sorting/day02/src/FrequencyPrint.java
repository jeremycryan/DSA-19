import java.util.HashMap;
import java.util.LinkedList;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Collections;

// O(NlogN) time, because sorting. O(N) space, because hash map.
public class FrequencyPrint {

    static String frequencyPrint(String s) {

        String[] words = s.split(" ");
        HashMap freqs = new HashMap<String, Integer>();
        int uniq_freq = 0;
        ArrayList<Integer> freq_vals = new ArrayList<Integer>();

        for (int i = 0; i < words.length; i++) {

            String this_word = words[i];
            int new_freq = (int)freqs.getOrDefault(this_word, 0) + 1;
            freqs.put(this_word, new_freq);
            if (new_freq == 1) {
                uniq_freq++;
            }

        }

        HashMap<Integer, LinkedList<String>> words_by_freq = new HashMap<Integer, LinkedList<String>>();
        Iterator map_iter = freqs.entrySet().iterator();

        while (map_iter.hasNext()) {

            HashMap.Entry<String, Integer> pair = (HashMap.Entry)map_iter.next();
            String this_word = pair.getKey();
            int this_freq = pair.getValue();
            freq_vals.add(this_freq);

            LinkedList<String> old_list = words_by_freq.getOrDefault(this_freq, new LinkedList<String>());
            old_list.push(" " + this_word);
            words_by_freq.put(this_freq, old_list);

        }

        Collections.sort(freq_vals);
        String return_string = "";

        int last_freq = 0;
        int checked_a_thing = 0;

        for (int i = 0; i < freq_vals.size(); i++) {

            if (freq_vals.get(i) == last_freq) continue;

            for (String word : words_by_freq.get(freq_vals.get(i))) {

                for (String sub_word : word.split(" ")) {

                    for (int j = 0; j < freq_vals.get(i); j++) {

                        if (sub_word.length() < 1) continue;
                        if (i + j > 0 || checked_a_thing == 1) return_string += " ";
                        return_string += sub_word;
                        checked_a_thing = 1;

                    }


                }

            }

            last_freq = freq_vals.get(i);

        }

        System.out.println(return_string);
        return return_string;
    }

}
