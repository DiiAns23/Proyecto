package Analizadores;
import Errores.Errores;
import java_cup.runtime.Symbol; 
import java.util.ArrayList;


public class Lexico implements java_cup.runtime.Scanner {
	private final int YY_BUFFER_SIZE = 512;
	private final int YY_F = -1;
	private final int YY_NO_STATE = -1;
	private final int YY_NOT_ACCEPT = 0;
	private final int YY_START = 1;
	private final int YY_END = 2;
	private final int YY_NO_ANCHOR = 4;
	private final int YY_BOL = 65536;
	private final int YY_EOF = 65537;
	private java.io.BufferedReader yy_reader;
	private int yy_buffer_index;
	private int yy_buffer_read;
	private int yy_buffer_start;
	private int yy_buffer_end;
	private char yy_buffer[];
	private int yychar;
	private int yyline;
	private boolean yy_at_bol;
	private int yy_lexical_state;

	public Lexico (java.io.Reader reader) {
		this ();
		if (null == reader) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(reader);
	}

	public Lexico (java.io.InputStream instream) {
		this ();
		if (null == instream) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(new java.io.InputStreamReader(instream));
	}

	private Lexico () {
		yy_buffer = new char[YY_BUFFER_SIZE];
		yy_buffer_read = 0;
		yy_buffer_index = 0;
		yy_buffer_start = 0;
		yy_buffer_end = 0;
		yychar = 0;
		yyline = 0;
		yy_at_bol = true;
		yy_lexical_state = YYINITIAL;
 
    yyline = 1; 
    yychar = 1; 
	}

	private boolean yy_eof_done = false;
	private final int YYINITIAL = 0;
	private final int yy_state_dtrans[] = {
		0
	};
	private void yybegin (int state) {
		yy_lexical_state = state;
	}
	private int yy_advance ()
		throws java.io.IOException {
		int next_read;
		int i;
		int j;

		if (yy_buffer_index < yy_buffer_read) {
			return yy_buffer[yy_buffer_index++];
		}

		if (0 != yy_buffer_start) {
			i = yy_buffer_start;
			j = 0;
			while (i < yy_buffer_read) {
				yy_buffer[j] = yy_buffer[i];
				++i;
				++j;
			}
			yy_buffer_end = yy_buffer_end - yy_buffer_start;
			yy_buffer_start = 0;
			yy_buffer_read = j;
			yy_buffer_index = j;
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}

		while (yy_buffer_index >= yy_buffer_read) {
			if (yy_buffer_index >= yy_buffer.length) {
				yy_buffer = yy_double(yy_buffer);
			}
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}
		return yy_buffer[yy_buffer_index++];
	}
	private void yy_move_end () {
		if (yy_buffer_end > yy_buffer_start &&
		    '\n' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
		if (yy_buffer_end > yy_buffer_start &&
		    '\r' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
	}
	private boolean yy_last_was_cr=false;
	private void yy_mark_start () {
		int i;
		for (i = yy_buffer_start; i < yy_buffer_index; ++i) {
			if ('\n' == yy_buffer[i] && !yy_last_was_cr) {
				++yyline;
			}
			if ('\r' == yy_buffer[i]) {
				++yyline;
				yy_last_was_cr=true;
			} else yy_last_was_cr=false;
		}
		yychar = yychar
			+ yy_buffer_index - yy_buffer_start;
		yy_buffer_start = yy_buffer_index;
	}
	private void yy_mark_end () {
		yy_buffer_end = yy_buffer_index;
	}
	private void yy_to_mark () {
		yy_buffer_index = yy_buffer_end;
		yy_at_bol = (yy_buffer_end > yy_buffer_start) &&
		            ('\r' == yy_buffer[yy_buffer_end-1] ||
		             '\n' == yy_buffer[yy_buffer_end-1] ||
		             2028/*LS*/ == yy_buffer[yy_buffer_end-1] ||
		             2029/*PS*/ == yy_buffer[yy_buffer_end-1]);
	}
	private java.lang.String yytext () {
		return (new java.lang.String(yy_buffer,
			yy_buffer_start,
			yy_buffer_end - yy_buffer_start));
	}
	private int yylength () {
		return yy_buffer_end - yy_buffer_start;
	}
	private char[] yy_double (char buf[]) {
		int i;
		char newbuf[];
		newbuf = new char[2*buf.length];
		for (i = 0; i < buf.length; ++i) {
			newbuf[i] = buf[i];
		}
		return newbuf;
	}
	private final int YY_E_INTERNAL = 0;
	private final int YY_E_MATCH = 1;
	private java.lang.String yy_error_string[] = {
		"Error: Internal error.\n",
		"Error: Unmatched input.\n"
	};
	private void yy_error (int code,boolean fatal) {
		java.lang.System.out.print(yy_error_string[code]);
		java.lang.System.out.flush();
		if (fatal) {
			throw new Error("Fatal Error.\n");
		}
	}
	private int[][] unpackFromString(int size1, int size2, String st) {
		int colonIndex = -1;
		String lengthString;
		int sequenceLength = 0;
		int sequenceInteger = 0;

		int commaIndex;
		String workString;

		int res[][] = new int[size1][size2];
		for (int i= 0; i < size1; i++) {
			for (int j= 0; j < size2; j++) {
				if (sequenceLength != 0) {
					res[i][j] = sequenceInteger;
					sequenceLength--;
					continue;
				}
				commaIndex = st.indexOf(',');
				workString = (commaIndex==-1) ? st :
					st.substring(0, commaIndex);
				st = st.substring(commaIndex+1);
				colonIndex = workString.indexOf(':');
				if (colonIndex == -1) {
					res[i][j]=Integer.parseInt(workString);
					continue;
				}
				lengthString =
					workString.substring(colonIndex+1);
				sequenceLength=Integer.parseInt(lengthString);
				workString=workString.substring(0,colonIndex);
				sequenceInteger=Integer.parseInt(workString);
				res[i][j] = sequenceInteger;
				sequenceLength--;
			}
		}
		return res;
	}
	private int yy_acpt[] = {
		/* 0 */ YY_NOT_ACCEPT,
		/* 1 */ YY_NO_ANCHOR,
		/* 2 */ YY_NO_ANCHOR,
		/* 3 */ YY_NO_ANCHOR,
		/* 4 */ YY_NO_ANCHOR,
		/* 5 */ YY_NO_ANCHOR,
		/* 6 */ YY_NO_ANCHOR,
		/* 7 */ YY_NO_ANCHOR,
		/* 8 */ YY_NO_ANCHOR,
		/* 9 */ YY_NO_ANCHOR,
		/* 10 */ YY_NO_ANCHOR,
		/* 11 */ YY_NO_ANCHOR,
		/* 12 */ YY_NO_ANCHOR,
		/* 13 */ YY_NO_ANCHOR,
		/* 14 */ YY_NO_ANCHOR,
		/* 15 */ YY_NO_ANCHOR,
		/* 16 */ YY_NO_ANCHOR,
		/* 17 */ YY_NO_ANCHOR,
		/* 18 */ YY_NO_ANCHOR,
		/* 19 */ YY_NO_ANCHOR,
		/* 20 */ YY_NO_ANCHOR,
		/* 21 */ YY_NO_ANCHOR,
		/* 22 */ YY_NO_ANCHOR,
		/* 23 */ YY_NO_ANCHOR,
		/* 24 */ YY_NO_ANCHOR,
		/* 25 */ YY_NO_ANCHOR,
		/* 26 */ YY_NO_ANCHOR,
		/* 27 */ YY_NO_ANCHOR,
		/* 28 */ YY_NO_ANCHOR,
		/* 29 */ YY_NO_ANCHOR,
		/* 30 */ YY_NOT_ACCEPT,
		/* 31 */ YY_NO_ANCHOR,
		/* 32 */ YY_NO_ANCHOR,
		/* 33 */ YY_NO_ANCHOR,
		/* 34 */ YY_NO_ANCHOR,
		/* 35 */ YY_NOT_ACCEPT,
		/* 36 */ YY_NO_ANCHOR,
		/* 37 */ YY_NOT_ACCEPT,
		/* 38 */ YY_NOT_ACCEPT,
		/* 39 */ YY_NOT_ACCEPT,
		/* 40 */ YY_NO_ANCHOR,
		/* 41 */ YY_NOT_ACCEPT,
		/* 42 */ YY_NO_ANCHOR
	};
	private int yy_cmap[] = unpackFromString(1,65538,
"7:9,26,4,7:2,3,7:18,26,6,9,31:2,25,31,8,7:2,24,23,19,28,18,1,27:10,17,21,5," +
"31,2,20,31,30:2,10,30:6,13,30:3,12,11,30:11,7,32,7:2,33,7,29:26,15,22,16,14" +
",7:65409,0:2")[0];

	private int yy_rmap[] = unpackFromString(1,43,
"0,1,2,1,3,1:4,4,1:11,5,1,6,1,6,1,7,1,6,8,9,10,11,1,12,1,13,14,15,16,15,6")[0];

	private int yy_nxt[][] = unpackFromString(17,34,
"1,2,3,4,5,31,36,6,7,8,9,42:3,10,11,12,13,14,15,16,17,18,19,20,32,4,21,22,23" +
",42,36,24,25,-1:35,30,-1:35,4,-1:22,4,-1:17,25,40,25:2,-1:13,25,-1,25:2,-1:" +
"2,25,-1:27,21,-1:16,25:4,-1:13,25,-1,25:2,-1:2,25,-1:4,34,-1:30,30:2,27,34," +
"30:29,-1:6,35,-1:52,26,-1:18,25:3,29,-1:13,25,-1,25:2,-1:2,25,-1,41,37,41:3" +
",38,41:27,-1:2,35,-1:32,35,28,35:31,-1,41,39,41:3,38,41:27,-1:10,25:2,33,25" +
",-1:13,25,-1,25:2,-1:2,25");

	public java_cup.runtime.Symbol next_token ()
		throws java.io.IOException {
		int yy_lookahead;
		int yy_anchor = YY_NO_ANCHOR;
		int yy_state = yy_state_dtrans[yy_lexical_state];
		int yy_next_state = YY_NO_STATE;
		int yy_last_accept_state = YY_NO_STATE;
		boolean yy_initial = true;
		int yy_this_accept;

		yy_mark_start();
		yy_this_accept = yy_acpt[yy_state];
		if (YY_NOT_ACCEPT != yy_this_accept) {
			yy_last_accept_state = yy_state;
			yy_mark_end();
		}
		while (true) {
			if (yy_initial && yy_at_bol) yy_lookahead = YY_BOL;
			else yy_lookahead = yy_advance();
			yy_next_state = YY_F;
			yy_next_state = yy_nxt[yy_rmap[yy_state]][yy_cmap[yy_lookahead]];
			if (YY_EOF == yy_lookahead && true == yy_initial) {
				return null;
			}
			if (YY_F != yy_next_state) {
				yy_state = yy_next_state;
				yy_initial = false;
				yy_this_accept = yy_acpt[yy_state];
				if (YY_NOT_ACCEPT != yy_this_accept) {
					yy_last_accept_state = yy_state;
					yy_mark_end();
				}
			}
			else {
				if (YY_NO_STATE == yy_last_accept_state) {
					throw (new Error("Lexical Error: Unmatched Input."));
				}
				else {
					yy_anchor = yy_acpt[yy_last_accept_state];
					if (0 != (YY_END & yy_anchor)) {
						yy_move_end();
					}
					yy_to_mark();
					switch (yy_last_accept_state) {
					case 1:
						
					case -2:
						break;
					case 2:
						{return new Symbol(sym.ASCII,yyline,yychar, yytext());}
					case -3:
						break;
					case 3:
						{return new Symbol(sym.NACION,yyline,yychar, yytext());}
					case -4:
						break;
					case 4:
						{}
					case -5:
						break;
					case 5:
						{yychar=1;}
					case -6:
						break;
					case 6:
						{return new Symbol(sym.TODO,yyline,yychar, yytext());}
					case -7:
						break;
					case 7:
						{return new Symbol(sym.COMILLASIMPLE,yyline,yychar, yytext());}
					case -8:
						break;
					case 8:
						{return new Symbol(sym.COMILLA,yyline,yychar, yytext());}
					case -9:
						break;
					case 9:
						{return new Symbol(sym.ALFAMAY,yyline, yychar, yytext());}
					case -10:
						break;
					case 10:
						{return new Symbol(sym.DIP,yyline,yychar, yytext());}
					case -11:
						break;
					case 11:
						{return new Symbol(sym.LLAVEI,yyline,yychar, yytext());}
					case -12:
						break;
					case 12:
						{return new Symbol(sym.LLAVED,yyline,yychar, yytext());}
					case -13:
						break;
					case 13:
						{return new Symbol(sym.DPUNTOS,yyline,yychar, yytext());}
					case -14:
						break;
					case 14:
						{return new Symbol(sym.PUNTO,yyline,yychar, yytext());}
					case -15:
						break;
					case 15:
						{return new Symbol(sym.COMA,yyline,yychar, yytext());}
					case -16:
						break;
					case 16:
						{return new Symbol(sym.INTERR,yyline,yychar, yytext());}
					case -17:
						break;
					case 17:
						{return new Symbol(sym.PTCOMA,yyline,yychar, yytext());}
					case -18:
						break;
					case 18:
						{return new Symbol(sym.BARRA,yyline,yychar, yytext());}
					case -19:
						break;
					case 19:
						{return new Symbol(sym.MAS,yyline,yychar, yytext());}
					case -20:
						break;
					case 20:
						{return new Symbol(sym.POR,yyline,yychar, yytext());}
					case -21:
						break;
					case 21:
						{return new Symbol(sym.ENTERO,yyline,yychar, yytext());}
					case -22:
						break;
					case 22:
						{return new Symbol(sym.ASIG,yyline,yychar, yytext());}
					case -23:
						break;
					case 23:
						{return new Symbol(sym.ALFAMIN,yyline, yychar, yytext());}
					case -24:
						break;
					case 24:
						{return new Symbol(sym.INVERBARRA,yyline, yychar, yytext());}
					case -25:
						break;
					case 25:
						{return new Symbol(sym.RAN,yyline, yychar, yytext());}
					case -26:
						break;
					case 26:
						{return new Symbol(sym.PORC,yyline,yychar, yytext());}
					case -27:
						break;
					case 27:
						{}
					case -28:
						break;
					case 28:
						{}
					case -29:
						break;
					case 29:
						{return new Symbol(sym.CONJUNTO,yyline,yychar, yytext());}
					case -30:
						break;
					case 31:
						{return new Symbol(sym.ASCII,yyline,yychar, yytext());}
					case -31:
						break;
					case 32:
						{return new Symbol(sym.TODO,yyline,yychar, yytext());}
					case -32:
						break;
					case 33:
						{return new Symbol(sym.RAN,yyline, yychar, yytext());}
					case -33:
						break;
					case 34:
						{}
					case -34:
						break;
					case 36:
						{return new Symbol(sym.ASCII,yyline,yychar, yytext());}
					case -35:
						break;
					case 40:
						{return new Symbol(sym.RAN,yyline, yychar, yytext());}
					case -36:
						break;
					case 42:
						{return new Symbol(sym.ALFAMAY,yyline, yychar, yytext());}
					case -37:
						break;
					default:
						yy_error(YY_E_INTERNAL,false);
					case -1:
					}
					yy_initial = true;
					yy_state = yy_state_dtrans[yy_lexical_state];
					yy_next_state = YY_NO_STATE;
					yy_last_accept_state = YY_NO_STATE;
					yy_mark_start();
					yy_this_accept = yy_acpt[yy_state];
					if (YY_NOT_ACCEPT != yy_this_accept) {
						yy_last_accept_state = yy_state;
						yy_mark_end();
					}
				}
			}
		}
	}
}
