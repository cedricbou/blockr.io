package io.blockr;

import static org.junit.Assert.fail;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import org.junit.Test;

public class BlockrTest {

	private final static String FIREHOL_FILE = "firehol_level1.netset.txt";

	@FunctionalInterface
	public interface Function2<One, Two, Return> {
		public Return appy(final One one, final Two two);
	}

	@Test
	public void test() {
		final BlockR br = new BlockR();
		loadFileInBlockr((ip, mask) -> br.addIp4(ip, mask));
	}

	private final Pattern isIpOrCidr = Pattern.compile("^\\s*(\\d{1,3}\\.)\\d{1,3}(\\/\\d{1,2}){0,1}\\s*.*");

	static private class MatcherIterator implements Iterator<String> {
		private final Matcher matcher;

		public MatcherIterator(Matcher matcher) {
			this.matcher = matcher;
		}

		public boolean hasNext() {
			return matcher.find();
		}

		public String next() {
			return matcher.group(0);
		}
	}

	private void loadFileInBlockr(Function2<String, Byte, BlockR> add) {
		try (final Stream<String> stream = Files.lines(Paths.get(getClass().getResource(FIREHOL_FILE).toURI()))) {
			//stream.flatMap(line -> new MatcherIterator(isIpOrCidr.matcher(line))).forEach(System.out::println);

		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}

	}

}
