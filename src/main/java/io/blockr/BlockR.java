package io.blockr;

import java.util.HashSet;

public class BlockR {
	
	private static final class Ip4 {
		public final String ip;
		public final Byte mask;
		
		public Ip4(final String ip, final Byte mask) {
			this.ip = ip;
			this.mask = mask;
		}
		
		@Override
		public boolean equals(Object obj) {
			return obj instanceof Ip4
					&& (ip.equals(((Ip4)obj).ip))
					&& (mask.equals(((Ip4)obj).mask));
		}
		
		@Override
		public int hashCode() {
			return ("ip" + "/" + mask.toString().trim()).hashCode();
		}
	}
	
	private final HashSet<Ip4> ip4s = new HashSet<Ip4>();
	
	public BlockR addIp4(final String ip, final Byte mask) {
		this.ip4s.add(new Ip4(ip, mask));
		return this;
	}
	
	public boolean contains(final String ip, final Byte mask) {
		return this.ip4s.contains(new Ip4(ip, mask));
	}

}
