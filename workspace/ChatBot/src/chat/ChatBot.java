package chat;

import java.io.FileInputStream;
import java.io.InputStream;

import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.tokenize.SimpleTokenizer;
import opennlp.tools.tokenize.Tokenizer;

public class ChatBot {
	Tokenizer token;
	POSTaggerME tagger = null;
	
	//Initialize chatbot - train tagger
	public ChatBot() {
		POSModel model = null;
		try (InputStream modelIn = new FileInputStream("en-pos-maxent.bin")) {
			model = new POSModel(modelIn);
			System.out.println("Model trained!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		token = new SimpleTokenizer();
		tagger = new POSTaggerME(model);

	}

	public String generateOutput(String input) {

		return null;
	}

	public void sentenceBreakdown(String input) {

	}

	public String[] tagNtokenize(String input) {
		String[] tokenized = token.tokenize(input);

		System.out.print("[");
		for (String s : tokenized)
			System.out.print(s + " ");
		System.out.print("]\n");

		String[] taggedSpeech = tagger.tag(tokenized);
			
		return taggedSpeech;
	}
}