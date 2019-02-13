package chat;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.tokenize.SimpleTokenizer;
import opennlp.tools.tokenize.Tokenizer;

public class Test {

	public static void main(String[] args) {
		@SuppressWarnings("deprecation")
		Tokenizer token = new SimpleTokenizer();

		POSModel model = null;

		try (InputStream modelIn = new FileInputStream("en-pos-maxent.bin")) {
			model = new POSModel(modelIn);
			System.out.println("Model trained!");
		} catch (Exception e) {
			e.printStackTrace();
		}

		POSTaggerME tagger = new POSTaggerME(model);
		Scanner in = new Scanner(System.in);

		while (true) {
			String input = in.nextLine();

			String[] tokenized = token.tokenize(input);

			System.out.print("[");
			for (String s : tokenized)
				System.out.print(s + " ");
			System.out.print("]\n");
			
			String[] taggedSpeech = tagger.tag(tokenized);

			System.out.print("[");
			for (String s : taggedSpeech)
				System.out.print(s + " ");
			System.out.print("]\n");

		}
	}

	public static ArrayList<String> cleanTokenized(String[] tokenized) {
		int size = tokenized.length;
		ArrayList<String> result = new ArrayList<String>();

		for (int i = 0; i < size; i++) {
			if (tokenized[i] != null) {
				if (tokenized[i].charAt(0) == 39) {
					result.add(tokenized[i].concat(tokenized[i + 1]));
					tokenized[i + 1] = null;
				} else
					result.add(tokenized[i]);
			}
		}

		return result;

	}

}
