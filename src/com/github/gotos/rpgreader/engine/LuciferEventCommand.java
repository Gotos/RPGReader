package com.github.gotos.rpgreader.engine;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class LuciferEventCommand implements UnitInterface {
	
	public long type		= 0;
	public long depth		= 0;
	public String string	= "";
	public long[] data;
	
	public static final int FREE_LINE						= 10;
	public static final int SHOW_MESSAGE					= 10110;
	public static final int ADD_LINE_TO_MESSAGE				= 20110;
	public static final int MESSAGE_OPTIONS					= 10120;
	public static final int SELECT_FACE						= 10130;
	public static final int SHOW_CHOICE						= 10140;
	public static final int CHOICE_CASE						= 20140;
	public static final int CHOICE_END						= 20141;
	public static final int INPUT_NUMBER					= 10150;
	public static final int CHANGE_SWITCH					= 10210;
	public static final int CHANGE_VARIABLE					= 10220; //Wenn ein Event als Datenherkunft gewählt wird, wird als 6ter Parameter die Event-ID übergeben. Hero folgt bei 10001.
	public static final int CHANGE_TIMER					= 10230;
	public static final int CHANGE_MONEY					= 10310;
	public static final int CHANGE_ITEMS					= 10320;
	public static final int CHANGE_PARTY					= 10330;
	public static final int CHANGE_EXP						= 10410;
	public static final int CHANGE_LEVEL					= 10420;
	public static final int CHANGE_STATS					= 10430;
	public static final int CHANGE_SKILL					= 10440;
	public static final int CHANGE_EQUIPMENT				= 10450;
	public static final int CHANGE_HP						= 10460;
	public static final int CHANGE_MP						= 10470;
	public static final int CHANGE_CONDITION				= 10480;
	public static final int FULL_RECOVERY					= 10490;
	public static final int INFLICT_DAMAGE					= 10500;
	public static final int CHANGE_HERO_NAME				= 10610;
	public static final int CHANGE_HERO_TITLE				= 10620;
	public static final int CHANGE_HERO_GRAPHIC				= 10630;
	public static final int CHANGE_HERO_FACE				= 10640;
	public static final int CHANGE_VEHICLE_GRAPHIC			= 10650;
	public static final int CHANGE_SYSTEM_BGM				= 10660;
	public static final int CHANGE_SYSTEM_SE				= 10670;
	public static final int CHANGE_SYSTEM_GRAPHIC			= 10680;
	public static final int CHANGE_TRANSITION				= 10690;
	public static final int START_COMBAT					= 10710;
	public static final int BATTLE_VICTORY					= 20710;
	public static final int BATTLE_ESCAPE					= 20711;
	public static final int BATTLE_DEFEAT					= 20712;
	public static final int BATTLE_END						= 20713;
	public static final int CALL_SHOP						= 10720;
	public static final int SHOP_TRANSACTION				= 20720;
	public static final int SHOP_CANCEL						= 20721;
	public static final int SHOP_END						= 20722;
	public static final int CALL_INN						= 10730;
	public static final int INN_REST						= 20730;
	public static final int INN_CANCEL						= 20731;
	public static final int INN_END							= 20732;
	public static final int ENTER_HERO_NAME					= 10740;
	public static final int TELEPORT						= 10810;
	public static final int MEMORIZE_LOCATION				= 10820;
	public static final int GO_TO_MEMORIZED_LOCATION		= 10830;
	public static final int RIDE_VEHICLE					= 10840;
	public static final int TELEPORT_VEHICLE				= 10850;
	public static final int SET_EVENT_LOCATION				= 10860;
	public static final int SWAP_TWO_EVENT_LOCATIONS		= 10870;
	public static final int GET_TERRAIN_ID					= 10910;
	public static final int GET_EVENT_ID					= 10920;
	public static final int ERASE_SCREEN					= 11010;
	public static final int SHOW_SCREEN						= 11020;
	public static final int SET_SCREEN_TONE					= 11030;
	public static final int FLASH_SCREEN					= 11040;
	public static final int SHAKE_SCREEN					= 11050;
	public static final int PAN_SCREEN						= 11060;
	public static final int WEATHER_EFFECTS					= 11070;
	public static final int SHOW_PICTURE					= 11110;
	public static final int MOVE_PICTURE					= 11120;
	public static final int ERASE_PICTURE					= 11130;
	public static final int SHOW_BATTLE_ANIMATION			= 11210;
	public static final int SET_HERO_OPACITY				= 11310;
	public static final int FLASH_EVENT						= 11320;
	public static final int MOVE_EVENT						= 11330;
	public static final int WAIT_UNTIL_MOVED				= 11340;
	public static final int STOP_ALL_MOVEMENT				= 11350;
	public static final int WAIT							= 11410;
	public static final int PLAY_BGM						= 11510;
	public static final int FADE_OUT_BGM					= 11520;
	public static final int MEMORIZE_BGM					= 11530;
	public static final int PLAY_MEMORIZED_BGM				= 11540;
	public static final int PLAY_SOUND_EFFECT				= 11550;
	public static final int PLAY_MOVIE						= 11560;
	public static final int KEY_INPUT_PROCESSING			= 11610;
	public static final int CHANGE_TILESET					= 11710;
	public static final int CHANGE_PANORAMA					= 11720;
	public static final int CHANGE_ENCOUNTER_RATE			= 11740;
	public static final int CHANGE_SINGLE_TILE				= 11750;
	public static final int CHANGE_TELEPORT_TARGET			= 11810;
	public static final int ENABLE_TELEPORT					= 11820;
	public static final int SET_ESCAPE_LOCATION				= 11830;
	public static final int ENABLE_ESCAPE					= 11840;
	public static final int CALL_SAVE_MENU					= 11910;
	public static final int ENABLE_SAVE						= 11930;
	public static final int CALL_MAIN_MENU					= 11950;
	public static final int ENABLE_MAIN_MENU				= 11960;
	public static final int FORK							= 12010;
	public static final int FORK_ELSE_CASE					= 22010;
	public static final int FORK_END						= 22011;
	public static final int LABEL							= 12110;
	public static final int JUMP_TO_LABEL					= 12120;
	public static final int START_LOOP						= 12210;
	public static final int END_LOOP						= 22210;
	public static final int BREAK_LOOP						= 12220;
	public static final int STOP_EVENT						= 12310;
	public static final int DELETE_EVENT					= 12320;
	public static final int CALL_EVENT						= 12330;
	public static final int COMMENT							= 12410;
	public static final int ADD_LINE_TO_COMMENT				= 22410;
	public static final int GAME_OVER						= 12420;
	public static final int GO_TO_TITLE_SCREEN				= 12510;
	public static final int CHANGE_CLASS					= 1008;
	public static final int CHANGE_BATTLE_COMMANDS			= 1009;
	public static final int CHANGE_ENEMY_HP					= 13110;
	public static final int CHANGE_ENEMY_MP					= 13120;
	public static final int CHANGE_ENEMY_CONDITION			= 13130;
	public static final int REVIVE_ENEMY					= 13150;
	public static final int CHANGE_BACKDROP					= 13210;
	public static final int SHOW_BATTLE_ANIMATION_IN_BATTLE	= 13260;
	public static final int ENABLE_COMBO					= 1007;
	public static final int FORCE_FLEE						= 1006;
	public static final int END_BATTLE						= 13410;
	public static final int FORK_IN_BATTLE					= 13310;
	public static final int FORK_IN_BATTLE_ELSE_CASE		= 23310;
	public static final int FORK_IN_BATTLE_END				= 23311;
	public static final int CALL_COMMON_EVENT				= 1005;
	
	public LuciferEventCommand(byte[] str) throws IOException {
		init(new DataReader(str));
	}
	
	public LuciferEventCommand(DataReader sr) throws IOException {
		init(sr);
	}
	
	public LuciferEventCommand(long t, long d, String s, long[] da) {
		type = t;
		depth = d;
		string = s;
		data = da;
	}
	
	private void init(DataReader sr) throws IOException {
		type = sr.nextInt();
		depth = sr.nextInt();
		long length = sr.nextInt();
		string = new String(sr.read(length, true), Encoder.ENCODING);
		length = sr.nextInt();
		data = new long[(int) length];
		for (int i = 0; i < length; i++) {
			data[i] = sr.nextInt(); // hier *muss* nextInt verwendet werden, da length angibt, wie viele argumente folgen, nicht deren gesamtlÃ¤nge.
		}
	}
	
	public byte[] write() {
		byte[] datablock = new byte[0];
		for (int i = 0; i < data.length; i++) {
			datablock = Helper.concatAll(datablock, DataReader.intToRPGint(data[i]));
		}
		try {
			return Helper.concatAll(DataReader.intToRPGint(type),
					DataReader.intToRPGint(depth),
					DataReader.intToRPGint(string.getBytes(Encoder.ENCODING).length),
					string.getBytes(Encoder.ENCODING),
					DataReader.intToRPGint(data.length),
					datablock);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return new byte[0];
		}
	}
	
	@Override
	public boolean equals(Object obj) {
	     if (this == obj) {
	        return true;
	     }
	     if (obj == null) {
	        return false;
	     }
	     if (!(obj instanceof LuciferEventCommand)) {
	        return false; // different class
	     }
	     
	     LuciferEventCommand o = (LuciferEventCommand) obj;
	     
	     return type == o.type
	     		&& depth == o.depth
	     		&& string.equals(o.string)
	     		&& Arrays.equals(data, o.data);
	}
}