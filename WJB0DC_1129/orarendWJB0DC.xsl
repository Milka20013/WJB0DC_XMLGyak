<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet xmlns:xsl = "http://www.w3.org/1999/XSL/Transform" version = "1.0" >
	<xsl:template match="/">
		<html>
			<body>
				<h2>Órarend</h2>
				<table>
					<tr>
						<th>ID</th>
						<th>Típus</th>
						<th>Óra</th>
						<th>Nap</th>
						<th>Óra</th>
						<th>Helyszín</th>
						<th>Oktató</th>
						<th>Szak</th>
					</tr>
					<xsl:for-each select="WJB0DC_orarend/ora">
						<tr>
							<td><xsl:value-of select = "@id"/></td>
							<td><xsl:value-of select = "@tipus"/></td>
							<td><xsl:value-of select = "targy"/></td>
							<td><xsl:value-of select = "idopont/nap"/></td>
							<td><xsl:value-of select = "idopont/tol"/>h - <xsl:value-of select = "idopont/ig"/>h</td>
							<td><xsl:value-of select = "helyszin"/></td>
							<td><xsl:value-of select = "oktato"/></td>
							<td><xsl:value-of select = "szak"/></td>
						</tr>
					</xsl:for-each>
				</table>
			</body>
		</html>
	</xsl:template>	
	<xsl:output method="html" encoding="utf-8" indent="yes"></xsl:output>

</xsl:stylesheet>