<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet xmlns:xsl = "http://www.w3.org/1999/XSL/Transform" version = "1.0" >
	<xsl:template match="/">
		<html>
			<body>
				<h2>Hallgatók</h2>
				<table>
					<tr>
						<th>ID</th>
						<th>Vezeteknév</th>
						<th>Keresztnév</th>
						<th>Becenév</th>
						<th>Kor</th>
						<th>Ösztöndíj</th>
					</tr>
					<xsl:for-each select="class/student">
						<tr>
							<td><xsl:value-of select = "@id"/></td>
							<td><xsl:value-of select = "vezeteknev"/></td>
							<td><xsl:value-of select = "keresztnev"/></td>
							<td><xsl:value-of select = "becenev"/></td>
							<td><xsl:value-of select = "kor"/></td>
							<td><xsl:value-of select = "osztondij"/></td>
						</tr>
					</xsl:for-each>
				</table>
			</body>
		</html>
	</xsl:template>	
	<xsl:output method="html" encoding="utf-8" indent="yes"></xsl:output>

</xsl:stylesheet>