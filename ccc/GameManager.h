#pragma once
#include "Player.h"
#include "GameObject.h"
#include <vector>

class GameManager //���� ��ü�� ����
{
public:
	GameManager();
	~GameManager();

	void tick(); //while������ ��� ���ư��� �Լ�, �̰� ��� �ൿ�� ������
	void drawCall(); // ȭ�鿡 �׸��׸��� �Լ�
	void CheckInput(); // ����Ű�� �Է¹޴� �Լ�
	void checkCoilision(); // �浹üũ�ϴ� �Լ�, �̱���
	void win(); //  �¸��ҋ� �θ��� �Լ�
	void lose();//  �й��Ҷ� �θ��� �Լ�
	void start(); // ������ �ٽ� �����Ҷ� �θ��� �Լ�, �̱���
	void deleteStage();
	int movePosX; // ������ �¿�� �����϶� �� ���� �������� ������
	int isPlaying;
	int isWin;
private:
	void Board(); // ���� �׸��� �Լ�
	Player* player; // �÷��̾�
	std::vector<GameObject*> gameObjects; // �Ѿ˰� ������ �����صδ� ����
	int score; // ����
};
