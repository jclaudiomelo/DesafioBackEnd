package com.DesafioBackEndJava.DesafioBackEndJava.enums;

public enum Regiao {
	NORDESTE(0.20),
	AMAZONIA(0.30),
	ARGENTINA(0.40),
	OUTROS(0.0);

	private final double taxa;

	Regiao(double taxa) {
		this.taxa = taxa;
	}

	public static Regiao fromString(String nome) {
		if (nome == null) return OUTROS;
		return switch (nome.toLowerCase()) {
			case "nordeste" -> NORDESTE;
			case "amazonia" -> AMAZONIA;
			case "argentina" -> ARGENTINA;
			default -> OUTROS;
		};
	}

	public double getTaxa() {
		return taxa;
	}
}
